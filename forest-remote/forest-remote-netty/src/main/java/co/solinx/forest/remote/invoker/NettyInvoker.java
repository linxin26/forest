package co.solinx.forest.remote.invoker;

import co.solinx.forest.remote.netty.client.NettyClient;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by LX on 2015/4/4.
 */
public class NettyInvoker {

    Logger logger = Logger.getLogger(NettyInvoker.class);

    public Object clientInvoker(Object delegate, String serverAddress, Method method) throws InterruptedException {
        NettyClient client = new NettyClient(delegate, method);
        client.start();
        logger.info("threadName:" + Thread.currentThread().getName());
        logger.info("method:"+method.getReturnType());
        if(method.getName()!="toString") {
            if(method.getReturnType().getName()!="void") {
                while (true) {
                    if (client.result() != null) {
                        break;
                    }
                }
            }
        }
        return client.result();
    }


}
