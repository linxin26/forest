package co.solinx.forest.remote.netty.Handler;

import co.solinx.forest.remote.exchange.Request;
import io.netty.buffer.UnpooledUnsafeDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * 请求引用服务
 * Created by LX on 2015/3/15.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


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
        Request request=new Request();
        request.setData(api.toString() + "," + method.getName());
//         ctx.channel().write(api.toString() + "," + method.getName());
//         ctx.channel().flush();
        ctx.writeAndFlush(request);
        logger.info(request);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelRegistered");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        UnpooledUnsafeDirectByteBuf byteBuf= (UnpooledUnsafeDirectByteBuf) msg;
//        byte[] rebytenew =new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(rebytenew);
        Request request= (Request) msg;
        this.result=request.getData();
    }

    public Object getRceiveMessage() {
        return result;
    }
}
