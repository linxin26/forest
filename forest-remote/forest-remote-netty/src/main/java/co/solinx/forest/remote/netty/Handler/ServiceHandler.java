package co.solinx.forest.remote.netty.Handler;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.*;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 处理所引用的服务
 * Created by LX on 2015/3/15.
 */
public class ServiceHandler extends SimpleChannelHandler {
    Logger logger = Logger.getLogger(ServiceHandler.class);

    List<Object> services;

    public ServiceHandler(List<Object> services) {
        this.services = services;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        super.messageReceived(ctx, e);
//        logger.info("HelloHandler MessageReceived");
        String message = (String) e.getMessage();
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
                        ctx.getChannel().write(result);
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

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        super.channelConnected(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        logger.info(e.getCause());
        super.exceptionCaught(ctx, e);
    }
}
