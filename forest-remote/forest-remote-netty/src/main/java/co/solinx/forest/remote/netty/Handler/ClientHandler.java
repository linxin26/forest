package co.solinx.forest.remote.netty.Handler;

import io.netty.buffer.UnpooledUnsafeDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * 请求引用服务
 * Created by LX on 2015/3/15.
 */
public class ClientHandler<T> extends ChannelInboundHandlerAdapter {


    Logger logger = Logger.getLogger(ClientHandler.class);
    Method method;
    Object api;
    Object result;

    public ClientHandler(Object api, Method method) {
        this.method = method;
        this.api = api;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelActive");
         ctx.channel().write(api.toString() + "," + method.getName());
         ctx.channel().flush();
//        ctx.write(api.toString() + "," + method.getName());
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelRegistered");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UnpooledUnsafeDirectByteBuf byteBuf= (UnpooledUnsafeDirectByteBuf) msg;
        byte[] rebytenew =new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(rebytenew);
        this.result=new String(rebytenew);
    }

    public Object getRceiveMessage() {
        return result;
    }
}
