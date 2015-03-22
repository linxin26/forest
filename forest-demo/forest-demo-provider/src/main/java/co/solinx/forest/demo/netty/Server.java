package co.solinx.forest.demo.netty;

import co.solinx.forest.demo.impl.HelloForestServiceImpl;
import co.solinx.forest.demo.impl.HelloRpcServiceImpl;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;

/**
 * Created by LX on 2015/3/21.
 */
public class Server {

    public static void main(String[] args) {

        ServerBootstrap server = new ServerBootstrap();
        server.setFactory(new NioServerSocketChannelFactory());
        server.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = new DefaultChannelPipeline();
                pipeline.addLast("decode", new ObjectDecoder());
                pipeline.addLast("encode", new ObjectEncoder());
                pipeline.addLast("handler", new ServerHandler(new Object[]{new HelloForestServiceImpl(),new HelloRpcServiceImpl()}));
                return pipeline;
            }
        });

        server.bind(new InetSocketAddress(19000));
    }

}
