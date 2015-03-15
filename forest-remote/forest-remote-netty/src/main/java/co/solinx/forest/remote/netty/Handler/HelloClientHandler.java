package co.solinx.forest.remote.netty.Handler;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * Created by LX on 2015/3/15.
 */
public class HelloClientHandler extends SimpleChannelHandler {

    Logger logger = Logger.getLogger(HelloClientHandler.class);

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        super.messageReceived(ctx, e);
        logger.info("HelloClientHandler messageReceived");
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        super.channelConnected(ctx, e);
        logger.info("HelloClientHandler channelConnected");
        ctx.getChannel().write("server hello ");
    }

    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.writeRequested(ctx, e);
//        ctx.getChannel().write("hello");
    }
}
