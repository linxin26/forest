package co.solinx.forest.remote.netty.server;

import co.solinx.forest.remote.netty.Handler.HelloHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by LX on 2015/3/15.
 */
public class Server {

    public ServerBootstrap server;

    public Server() {
        server = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
    }

    public void start() {

        server.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("encode", new StringEncoder());
                pipeline.addLast("decode", new StringDecoder());
                pipeline.addLast("handler", new HelloHandler());
                return pipeline;
            }
        });
        server.bind(new InetSocketAddress("127.0.0.1", 18088));

    }


}
