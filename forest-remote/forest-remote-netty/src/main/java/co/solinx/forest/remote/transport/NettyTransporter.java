package co.solinx.forest.remote.transport;

import co.solinx.forest.common.ResponseFuture;
import co.solinx.forest.remote.exchange.DefaultFuture;
import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.exchange.Response;
import co.solinx.forest.remote.netty.client.NettyClient;
import org.apache.log4j.Logger;

/**
 * Created by linx on 2015-05-13.
 * netty Transporter
 */
public class NettyTransporter implements ITransporter {


    Logger logger=Logger.getLogger(NettyTransporter.class);
    Response response;
    NettyClient client;

    public NettyTransporter() {
        client=new NettyClient();
    }

    public void open(String address,int port,ITransporter transporter){
        client.open(address, port, transporter);
    }

    @Override
    public void connect() {
        client.connect();
    }

    @Override
    public void bind() {

    }

    @Override
    public ResponseFuture send(Object obj){

        DefaultFuture future=new DefaultFuture((Request) obj);
//        ChannelFuture future= client.send(obj);
        client.send(obj);
//        try {
//            future.await(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        logger.info(obj);
//        while (true){
////            logger.info(response);
//            try {
//                future.await(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (response!=null) {
//                Request request = (Request) obj;
//                if (response.getId() == request.getId()) {
//                    break;
//                }
//            }
//        }
//        logger.info(response);
        return future;
    }


    public void received(Response response){

        this.response=response;
    }

}
