package co.solinx.forest.remote.transport;

import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.exchange.Response;
import co.solinx.forest.remote.netty.client.NettyClient;
import io.netty.channel.ChannelFuture;
import org.apache.log4j.Logger;

/**
 * Created by linx on 2015-05-13.
 * netty Transporter
 */
public class NettyTransporter implements ITransporter {


    Logger logger=Logger.getLogger(NettyTransporter.class);
    Response response;
    NettyClient client;

    @Override
    public void connect(String address,int port,ITransporter transporter) {
         client=new NettyClient(address,port,transporter);
          client.doConnect();
    }

    @Override
    public void bind() {

    }

    @Override
    public Response send(Object obj){

        ChannelFuture future= client.send(obj);
        try {
            future.await(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(obj);
        while (true){
//            logger.info(response);
            try {
                future.await(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (response!=null) {
                Request request = (Request) obj;
                if (response.getId() == request.getId()) {
                    break;
                }
            }
        }
        logger.info(response);
        return response;
    }


    public void received(Response response){

        this.response=response;
    }

}
