package co.solinx.forest.remote.netty.test;

import co.solinx.forest.remote.netty.test.handler.*;
import co.solinx.forest.common.utils.InetAddressUtils;
import co.solinx.forest.remote.netty.test.code.RequestDecoder;
import co.solinx.forest.remote.netty.test.code.RequestEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * Created by LX on 2015/3/15.
 */
public class TestServer {

    public static void main(String[] args) {
         ServerBootstrap server;
        server = new ServerBootstrap();
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        server.group(boosGroup, workerGroup);
        server.channel(NioServerSocketChannel.class);
        server.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast("encoder", new RequestEncoder());
                sc.pipeline().addLast(new RequestDecoder());
                sc.pipeline().addLast(new ServerHandler());
            }
        });
        server.bind(new InetSocketAddress(InetAddressUtils.findAddress(), 18088));
    }

}
