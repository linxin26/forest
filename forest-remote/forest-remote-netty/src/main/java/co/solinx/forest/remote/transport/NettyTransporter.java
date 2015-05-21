package co.solinx.forest.remote.transport;

import co.solinx.forest.remote.netty.client.NettyClient;
import io.netty.channel.Channel;

/**
 * Created by linx on 2015-05-13.
 * netty Transporter
 */
public class NettyTransporter implements ITransporter {


    Channel channel;

    @Override
    public void connect(String address,int port) {
        NettyClient client=new NettyClient(address,port);
          client.doConnect();
         channel=client.getChannel();
    }

    @Override
    public void bind() {

    }

    @Override
    public void send(Object obj){

        channel.writeAndFlush(obj);
    }


}
