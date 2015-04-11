package co.solinx.forest.remote.netty.test.handler;

import co.solinx.forest.remote.exchange.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

/**
 * Created by linx on 2015/4/11.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    Logger logger=Logger.getLogger(ServerHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("ServerHandler");
//        System.out.println("server active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg);
        Request request= (Request) msg;
        logger.info(request);
        logger.info(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.getCause().printStackTrace();
    }
}
