package co.solinx.forest.remote.netty.server;

import co.solinx.forest.remote.netty.Handler.HelloHandler;
import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by LX on 2015/3/15.
 */
public class NettyServer {

    Logger logger = Logger.getLogger(NettyServer.class);

    public ServerBootstrap server;
    private static NettyServer nettyServer;

    private NettyServer() {

    }

    public static NettyServer getInstance() {
        if (nettyServer == null) {
            nettyServer = new NettyServer();
        }
        return nettyServer;
    }

    public void open() {
        if (server == null) {
            server = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
            this.start();
        }
    }

    private void start() {
        server.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("encode", new ObjectEncoder());
                pipeline.addLast("decode", new ObjectDecoder());
                pipeline.addLast("handler", new HelloHandler());
                return pipeline;
            }
        });
        server.bind(new InetSocketAddress("127.0.0.1", 18088));
        logger.info("netty server start bind by 18088 ");
    }


}
