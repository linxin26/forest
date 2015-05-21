package co.solinx.forest.remote.transport;

/**
 * Created by linx on 2015-05-13.
 */
public interface ITransporter {

     void connect(String address,int port);

     void bind();

     void send(Object obj);

}
