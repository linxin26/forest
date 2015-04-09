package co.solinx.forest.remote.netty.Handler;

import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.buffer.UnpooledUnsafeDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * 处理所引用的服务
 * Created by LX on 2015/3/15.
 */
public class ServiceHandler extends ChannelInboundHandlerAdapter {
    Logger logger = Logger.getLogger(ServiceHandler.class);

    List<Object> services;

    public ServiceHandler(List<Object> services) {
        this.services = services;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

//        UnpooledDirectByteBuf byteBuf=new UnpooledDirectByteBuf();
//        byteBuf.readBytes(msg);
        UnpooledUnsafeDirectByteBuf byteBuf= (UnpooledUnsafeDirectByteBuf) msg;
        byte[] rebytenew =new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(rebytenew);
        String message =new String(rebytenew);
        logger.info("message:" + message);
        logger.info(services.size());
        String[] invoke = message.split(",");
        Class<?>[] parameterTypes = null;
        Object[] arguments = null;
        try {
            for (int i = 0; i < services.size(); i++) {
                if (this.isInterfaceImpl(services.get(i), invoke[0])) {
                    Method method = services.get(i).getClass().getMethod(invoke[1], parameterTypes);
                    Object result = method.invoke(services.get(i), arguments);
                    logger.info("result:" + result);
                    if(result!=null) {
                        ctx.channel().write(result);
                        ctx.channel().flush();
                    }
                } else {

                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    /**
     * 检查czl是否实现intl接口
     *
     * @param czl
     * @param intl
     * @return
     */
    private boolean isInterfaceImpl(Object czl, String intl) {
        Class[] interfaces = czl.getClass().getInterfaces();
        boolean ret = false;
        for (int i = 0; i < interfaces.length; i++) {
            if (interfaces[i].getName().equals(intl)) {
                ret = true;
            }
        }
        return ret;
    }


}
