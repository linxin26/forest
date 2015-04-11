package co.solinx.forest.remote.netty.test.handler;

import co.solinx.forest.remote.exchange.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by linx on 2015/4/11.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Request request=new Request();
        request.setId(2);
        request.setData("clientHandler ..............hi");
        ctx.writeAndFlush(request);
//        System.out.println("clientHandler");
//        ctx.writeAndFlush(new Integer(1000));

    }



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        System.out.println("server channelRead");

    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.getCause().printStackTrace();
    }
}
