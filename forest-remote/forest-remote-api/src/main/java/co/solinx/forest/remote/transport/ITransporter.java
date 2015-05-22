package co.solinx.forest.remote.transport;

import co.solinx.forest.remote.exchange.Response;

/**
 * Created by linx on 2015-05-13.
 */
public interface ITransporter {

     void connect(String address,int port,ITransporter transporter);

     void bind();

     Response send(Object obj);

    void received(Response response);

}
