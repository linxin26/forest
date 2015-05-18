package co.solinx.forest.remote.transport;

import co.solinx.forest.remote.netty.client.NettyClient;
import io.netty.bootstrap.Bootstrap;

/**
 * Created by linx on 2015-05-13.
 * netty Transporter
 */
public class NettyTransporter implements ITransporter {

    @Override
    public void connect(String address,int port) {
        NettyClient client=new NettyClient(address,port);
        Bootstrap nettyClient= client.doConnect();

    }

    @Override
    public void bind() {

    }
}
