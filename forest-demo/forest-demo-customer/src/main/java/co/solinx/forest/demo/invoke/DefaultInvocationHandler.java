package co.solinx.forest.demo.invoke;

import co.solinx.forest.demo.netty.ClientHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * Created by LX on 2015/3/21.
 */
public class DefaultInvocationHandler implements InvocationHandler, Serializable {


    boolean result = false;
    Object obj;

    @Override
    public Object invoke(Object proxy, final Method method, Object[] args) throws Throwable {

        ClientBootstrap client = new ClientBootstrap();
        final ClientHandler clientHandler = new ClientHandler(obj, method);
        client.setFactory(new NioClientSocketChannelFactory());
        client.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = new DefaultChannelPipeline();
                pipeline.addLast("decode", new ObjectDecoder());
                pipeline.addLast("encode", new ObjectEncoder());
                pipeline.addLast("handler", clientHandler);
                System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
                System.out.println(1);
                return pipeline;
            }
        });

        client.connect(new InetSocketAddress(19000));
//        while(!result){
//            System.out.println(result);
//        }
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
        System.out.println(2);
        Object ret = clientHandler.getObj();
//        client.releaseExternalResources();
        return ret;
    }

    public DefaultInvocationHandler(Object obj) {
        this.obj = obj;
    }

    public void closeConnection() {
    }
}
