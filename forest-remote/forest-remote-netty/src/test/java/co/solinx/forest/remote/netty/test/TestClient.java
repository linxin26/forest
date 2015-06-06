package co.solinx.forest.remote.netty.test;



import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.netty.test.handler.*;
import co.solinx.forest.remote.netty.test.code.RequestDecoder;
import co.solinx.forest.remote.netty.test.code.RequestEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * Created by LX on 2015/3/15.
 */
public class TestClient {
    public static void main(String[] args) {
        Bootstrap client;
        client = new Bootstrap();
        client.group(new NioEventLoopGroup());
        client.channel(NioSocketChannel.class);
        client.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(new RequestDecoder());
                sc.pipeline().addLast(new RequestEncoder());
                sc.pipeline().addLast(new ClientHandler());

            }
        });
        ChannelFuture future= client.connect(new InetSocketAddress("127.0.0.1", 18088));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         future= client.connect(new InetSocketAddress("127.0.0.1", 18088));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Request request=new Request();
        request.setId(99);
        request.setData("clientHandler ..............9999999");
        future.channel().writeAndFlush(request);
    }
}
