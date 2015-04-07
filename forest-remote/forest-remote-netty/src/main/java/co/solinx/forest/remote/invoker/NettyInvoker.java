package co.solinx.forest.remote.invoker;

import co.solinx.forest.remote.netty.client.NettyClient;

import java.lang.reflect.Method;

/**
 * Created by LX on 2015/4/4.
 */
public class NettyInvoker {

    public Object clientInvoker(Object delegate,String serverAddress,Method method) throws InterruptedException {
        NettyClient client=new NettyClient(delegate,method);
        client.start();
        Thread.sleep(2000);
        return client.result();
    }


}
