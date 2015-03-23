package co.solinx.forest.remote.proxy;

import co.solinx.forest.remote.netty.Handler.ClientHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * 通过netty引用远程服务
 * Created by LX on 2015/3/21.
 */
public class DefaultInvocationHandler implements InvocationHandler,Serializable {


    boolean result = false;
    Object obj;

    @Override
    public Object invoke(Object proxy, final Method method, Object[] args) throws Throwable {

        //启动ntty客户端
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
                return pipeline;
            }
        });

        client.connect(new InetSocketAddress("127.0.0.1", 18088));
//        while(!result){
//            System.out.println(result);
//        }
        Thread.sleep(2000);
        Object ret = clientHandler.getApi();
//        client.releaseExternalResources();
        return ret;
    }

    public DefaultInvocationHandler(Object obj) {
        this.obj = obj;
    }

}
