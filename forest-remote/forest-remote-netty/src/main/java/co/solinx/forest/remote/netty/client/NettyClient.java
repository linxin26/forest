package co.solinx.forest.remote.netty.client;

import co.solinx.forest.remote.netty.Handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ChannelFactory;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;

/**
 * netty客户端
 * Created by LX on 2015/3/15.
 */
public class NettyClient {

    Logger logger=Logger.getLogger(NettyClient.class);
    Bootstrap client;
    ClientHandler clientHandler;

    public NettyClient(Object service, Method method) {
        client = new Bootstrap();
        client.group(new NioEventLoopGroup());
        clientHandler = new ClientHandler(service, method);
    }

    public void start() {

        client.channel(NioSocketChannel.class);
        client.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addFirst(new StringDecoder());
                sc.pipeline().addFirst(new StringEncoder());
                sc.pipeline().addFirst(clientHandler);
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
