package co.solinx.forest.remote.netty.client;

import co.solinx.forest.remote.netty.Handler.ClientHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * netty客户端
 * Created by LX on 2015/3/15.
 */
public class NettyClient {

    ClientBootstrap client;
    ClientHandler clientHandler;

    public NettyClient(Object service, Method method) {
        client = new ClientBootstrap(new NioClientSocketChannelFactory());
        clientHandler = new ClientHandler(service, method);
    }

    public void start() {

        client.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("encode", new ObjectEncoder());
                pipeline.addLast("decode", new ObjectDecoder());
                pipeline.addLast("handler", clientHandler);
                return pipeline;
            }
        });
        client.connect(new InetSocketAddress("127.0.0.1", 18088));
    }

    public Object result() {
        return clientHandler.getRceiveMessage();
    }

}
