package co.solinx.forest.remote.netty.Handler;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * Created by LX on 2015/3/15.
 */
public class HelloClientHandler extends SimpleChannelHandler {

    Logger logger = Logger.getLogger(HelloClientHandler.class);
    String service;

    public HelloClientHandler(String service) {
        this.service = service;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        super.messageReceived(ctx, e);
        Object obj;
        byte[] bytes= (byte[]) e.getMessage();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        obj = ois.readObject();
        ois.close();
        bis.close();
        logger.info(bytes.length);
        logger.info(obj);
        logger.info("HelloClientHandler messageReceived");
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        super.channelConnected(ctx, e);
        logger.info("HelloClientHandler channelConnected");
        ctx.getChannel().write(service);
//        ctx.getChannel().write(Class.forName("co.solinx.forest.remote.netty.main").newInstance());
//        ctx.getChannel().close();
    }

    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.writeRequested(ctx, e);
//        ctx.getChannel().write("hello");
    }
}
