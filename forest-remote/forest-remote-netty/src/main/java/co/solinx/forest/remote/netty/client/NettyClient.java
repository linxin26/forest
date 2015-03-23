package co.solinx.forest.remote.netty.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * netty客户端
 * Created by LX on 2015/3/15.
 */
public class NettyClient {
    ClientBootstrap client;

    public NettyClient() {
        client = new ClientBootstrap(new NioClientSocketChannelFactory());

    }

    public void start(Class service) {
//        final ClientHandler clientHandler = new ClientHandler(service);
//        client.setPipelineFactory(new ChannelPipelineFactory() {
//            @Override
//            public ChannelPipeline getPipeline() throws Exception {
//                ChannelPipeline pipeline = Channels.pipeline();
//                pipeline.addLast("encode", new ObjectEncoder());
//                pipeline.addLast("decode", new ObjectDecoder());
//                pipeline.addLast("handler", clientHandler);
//                return pipeline;
//            }
//        });
//        client.connect(new InetSocketAddress("127.0.0.1", 18088));
    }

    public void result(){

    }

}
