package co.solinx.forest.remote.netty.Handler;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by LX on 2015/3/15.
 */
public class HelloClientHandler<T> extends SimpleChannelHandler {

    Logger logger = Logger.getLogger(HelloClientHandler.class);
    Class<T> service;

    public HelloClientHandler(Class<T> service) {
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
//        ctx.getChannel().write(service);
//        ctx.getChannel().write(Class.forName("co.solinx.forest.remote.netty.main").newInstance());
//        ctx.getChannel().close();
        logger.info(service);
        T obj=(T) Proxy.newProxyInstance(service.getClassLoader(),new Class[]{service},new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                return method.invoke(service,args);
            }
        });
        logger.info("invoke");
        logger.info(obj.getClass().getMethods());
    }

}
