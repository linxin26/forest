package co.solinx.forest.remote.netty.Handler;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * Created by LX on 2015/3/15.
 */
public class HelloHandler extends SimpleChannelHandler {
    Logger logger = Logger.getLogger(HelloHandler.class);

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        super.messageReceived(ctx, e);
        logger.info(e.getMessage().toString());
        logger.info("HelloHandler MessageReceived");
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        super.channelConnected(ctx, e);
        logger.info("HelloHandler channelConnected");
    }


}
