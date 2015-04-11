package co.solinx.forest.remote.netty.client;

import co.solinx.forest.remote.netty.Handler.ClientHandler;
import co.solinx.forest.remote.netty.code.RequestDecoder;
import co.solinx.forest.remote.netty.code.RequestEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * netty客户端
 * Created by LX on 2015/3/15.
 */
public class NettyClient {

    Logger logger=Logger.getLogger(NettyClient.class);
    Bootstrap client;
    ClientHandler clientHandler;
    NioEventLoopGroup eventLoopGroup=new NioEventLoopGroup();

    public NettyClient(Object service, Method method) {
        client = new Bootstrap();
        client.group(eventLoopGroup);
        clientHandler = new ClientHandler(service, method);
    }

    public void start() {

        try {
            client.channel(NioSocketChannel.class);
            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast(new RequestDecoder());
                    sc.pipeline().addLast(new RequestEncoder());
                    sc.pipeline().addLast(clientHandler);

                }
            });
            ChannelFuture future = client.connect(new InetSocketAddress("127.0.0.1", 18088));
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    logger.info("rceiveMessage:" + clientHandler.getRceiveMessage());
                }
            });
        }finally{
//            eventLoopGroup.shutdownGracefully();
        }
    }

    public Object result() {
        return clientHandler.getRceiveMessage();
    }

}
