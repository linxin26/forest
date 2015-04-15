package co.solinx.forest.remote.netty.server;

import co.solinx.forest.common.utils.InetAddressUtils;
import co.solinx.forest.remote.netty.Handler.ServiceHandler;
import co.solinx.forest.remote.netty.code.Decoder;
import co.solinx.forest.remote.netty.code.Encoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by LX on 2015/3/15.
 */
public class NettyServer {

    Logger logger = Logger.getLogger(NettyServer.class);

    public ServerBootstrap server;
    private static NettyServer nettyServer;
    private List<Object> serviceList;

    private NettyServer() {

    }

    public static NettyServer getInstance() {
        if (nettyServer == null) {
            nettyServer = new NettyServer();
        }
        return nettyServer;
    }

    public void open(List<Object> serviceList, int port) {
        if (server == null) {
            this.serviceList = serviceList;
            server = new ServerBootstrap();
            EventLoopGroup boosGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            server.group(boosGroup, workerGroup);
            this.start(port);
            logger.info("------------------netty start");
        }
    }

    private void start(int port) {
        final List<Object> services = serviceList;
        server.channel(NioServerSocketChannel.class);
        server.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(new Encoder());
                sc.pipeline().addLast(new Decoder());
                sc.pipeline().addLast(new ServiceHandler(services));
            }
        });

        server.bind(new InetSocketAddress(InetAddressUtils.findAddress(), port));
        logger.info("netty server start bind by " + port);
    }


}
