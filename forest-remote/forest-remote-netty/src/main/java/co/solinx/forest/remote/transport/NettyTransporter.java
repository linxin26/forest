package co.solinx.forest.remote.transport;

import co.solinx.forest.remote.exchange.Response;
import co.solinx.forest.remote.netty.client.NettyClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.apache.log4j.Logger;

/**
 * Created by linx on 2015-05-13.
 * netty Transporter
 */
public class NettyTransporter implements ITransporter {


    Logger logger=Logger.getLogger(NettyTransporter.class);
    Channel channel;
    Response response;

    @Override
    public void connect(String address,int port,ITransporter transporter) {
        NettyClient client=new NettyClient(address,port,transporter);
          client.doConnect();
         channel=client.getChannel();
    }

    @Override
    public void bind() {

    }

    @Override
    public Response send(Object obj){

        ChannelFuture future= channel.writeAndFlush(obj);
        try {
            future.await(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Response");
        logger.info(response);
        return response;
    }


    public void received(Response response){

        this.response=response;
    }

}
