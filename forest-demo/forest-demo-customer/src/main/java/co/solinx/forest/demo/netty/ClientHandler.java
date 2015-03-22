package co.solinx.forest.demo.netty;

import org.jboss.netty.channel.*;

import java.lang.reflect.Method;

/**
 * Created by LX on 2015/3/21.
 */
public class ClientHandler<T> extends SimpleChannelHandler {

    Object api;
    Method method;
    Object obj;

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        obj = e.getMessage();
        System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
        System.out.println(3);
        System.out.println("clientHandler:" + e.getMessage());
        ctx.getChannel().disconnect();

    }

    @Override
    public void channelConnected(final ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
        ctx.getChannel().write(api.toString()+","+method.getName());
    }

    public <T> ClientHandler(Object proxy, Method method) {
        this.api = proxy;
        this.method = method;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println(e.getCause());
        super.exceptionCaught(ctx, e);
    }

    public Object getObj() {
        return obj;
    }
}
