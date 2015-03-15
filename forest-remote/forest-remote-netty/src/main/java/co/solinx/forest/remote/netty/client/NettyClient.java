package co.solinx.forest.remote.netty.client;

import co.solinx.forest.remote.netty.Handler.HelloClientHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * Created by LX on 2015/3/15.
 */
public class NettyClient {
    ClientBootstrap client;

    public NettyClient() {
        client = new ClientBootstrap(new NioClientSocketChannelFactory());

    }

    public void start() {
        client.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline=Channels.pipeline();
                pipeline.addLast("encode",new StringEncoder());
                pipeline.addLast("decode",new StringDecoder());
                pipeline.addLast("handler",new HelloClientHandler());
                return pipeline;
            }
        });
        client.connect(new InetSocketAddress("127.0.0.1", 18088));
    }


}
