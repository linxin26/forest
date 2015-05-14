package co.solinx.forest.remote.transport;

import co.solinx.forest.remote.netty.client.NettyClient;
import io.netty.bootstrap.Bootstrap;

/**
 * Created by linx on 2015-05-13.
 */
public class NettyTransporter implements ITransporter {

    @Override
    public void connect(String address) {
        NettyClient client=new NettyClient(address);
        Bootstrap nettyClient= client.doConnect();
//        nettyClient.
    }

    @Override
    public void bind() {

    }
}
