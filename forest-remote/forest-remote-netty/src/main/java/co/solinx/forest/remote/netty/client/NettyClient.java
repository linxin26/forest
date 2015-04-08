package co.solinx.forest.remote.netty.client;

import co.solinx.forest.remote.netty.Handler.ClientHandler;
import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
import sun.rmi.runtime.Log;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * netty客户端
 * Created by LX on 2015/3/15.
 */
public class NettyClient {

    Logger logger=Logger.getLogger(NettyClient.class);

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
        ChannelFuture future= client.connect(new InetSocketAddress("127.0.0.1", 18088));
         future.addListener(new ChannelFutureListener() {
             @Override
             public void operationComplete(ChannelFuture future) throws Exception {
                 logger.info("rceiveMessage:"+clientHandler.getRceiveMessage());
             }
         });
    }

    public Object result() {
        return clientHandler.getRceiveMessage();
    }

}
