package co.solinx.forest.remote.netty.Handler;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by LX on 2015/3/15.
 */
public class HelloHandler extends SimpleChannelHandler {
    Logger logger = Logger.getLogger(HelloHandler.class);

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        super.messageReceived(ctx, e);
        logger.info(e.getMessage().toString());
        byte[] bytes = null;
        Object obj=Class.forName(e.getMessage().toString()).newInstance();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        bytes = bos.toByteArray ();
        oos.close();
        bos.close();
        logger.info(obj);
        ctx.getChannel().write(bytes);
        logger.info("HelloHandler MessageReceived");
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        super.channelConnected(ctx, e);
//        ctx.getChannel().write("Server Handler");
        logger.info("HelloHandler channelConnected");
    }


}
