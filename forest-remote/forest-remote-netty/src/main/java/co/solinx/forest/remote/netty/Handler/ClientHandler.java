package co.solinx.forest.remote.netty.Handler;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.*;

import java.lang.reflect.Method;

/**
 * Created by LX on 2015/3/15.
 */
public class ClientHandler<T> extends SimpleChannelHandler {

    Logger logger = Logger.getLogger(ClientHandler.class);
    Method method;
    Object api;

    public ClientHandler(Object api, Method method) {
        this.method = method;
        this.api = api;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        super.messageReceived(ctx, e);
        api = e.getMessage();
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        super.channelConnected(ctx, e);
        logger.info("HelloClientHandler channelConnected");
        ctx.getChannel().write(api.toString() + "," + method.getName());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        logger.info(e.getCause());
        super.exceptionCaught(ctx, e);
    }

    public Object getApi() {
        return api;
    }
}
