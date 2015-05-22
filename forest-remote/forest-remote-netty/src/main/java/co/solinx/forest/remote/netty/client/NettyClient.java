package co.solinx.forest.remote.netty.client;

import co.solinx.forest.remote.Client;
import co.solinx.forest.remote.exchange.Response;
import co.solinx.forest.remote.netty.Handler.ClientHandler;
import co.solinx.forest.remote.netty.code.Decoder;
import co.solinx.forest.remote.netty.code.Encoder;
import co.solinx.forest.remote.transport.ITransporter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * netty客户端
 * Created by LX on 2015/3/15.
 */
public class NettyClient implements Client{

    Logger logger = Logger.getLogger(NettyClient.class);
    Bootstrap client;
    ClientHandler clientHandler;
    NioEventLoopGroup eventLoopGroup;
    Object service;
    Method method;     //方法
    Object[] params;   //参数
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private Channel channel;

    private String address;
    private Integer port=18089;
    private ITransporter transporter;


    public NettyClient(Object service, Method method, Object[] params) {
        client = new Bootstrap();
        eventLoopGroup = new NioEventLoopGroup();
        this.service = service;
        this.method = method;
        this.params = params;
    }

    public NettyClient(String address,int port,ITransporter transporter){
        this.address=address;
        this.port=port;
        client = new Bootstrap();
        eventLoopGroup = new NioEventLoopGroup();
        this.transporter=transporter;
    }

    public void doConnect(){
        client.group(eventLoopGroup);
        client.channel(NioSocketChannel.class);
        client.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(new Decoder(transporter));
                sc.pipeline().addLast(new Encoder());
            }
        });
        logger.info("connect to " + address + ":" + port);
        ChannelFuture future= client.connect(new InetSocketAddress(address, port));
        NettyClient.this.channel=future.channel();
    }


    public void start(String address) {

        lock.lock();
        try {
            client.group(eventLoopGroup);
            clientHandler = new ClientHandler(service, method, params);
            client.channel(NioSocketChannel.class);
            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast(new Decoder());
                    sc.pipeline().addLast(new Encoder());
                    sc.pipeline().addLast(clientHandler);

                }
            });
            ChannelFuture future = client.connect(new InetSocketAddress(address.split(":")[0], Integer.parseInt(address.split(":")[1])));

            //用于检查handler是否已返回数据
            while (true) {
                Response result = clientHandler.getRceiveMessage();
                if (result == null || result.getResult().equals("null") || result.getResult().equals("")) {
                    condition.await(80, TimeUnit.MILLISECONDS);
                } else {
                    break;
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
        lock.unlock();
    }


    public Object result() {
        return clientHandler.getRceiveMessage().getResult();
    }

    @Override
    public void connect() {

    }

    public Channel getChannel() {
        return channel;
    }

}
