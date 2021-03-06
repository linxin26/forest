package co.solinx.forest.remote.invoker;

import co.solinx.forest.remote.netty.client.NettyClient;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by LX on 2015/4/4.
 */
public class NettyInvoker {

    Logger logger = Logger.getLogger(NettyInvoker.class);

    public Object clientInvoker(Object delegate, String serverAddress, Method method, Object[] params) throws InterruptedException {
        NettyClient client = new NettyClient(delegate, method, params);
        if (method.getName() != "toString") {
            logger.info("serverAddress:" + serverAddress);
            client.start(serverAddress);
            logger.info("threadName:" + Thread.currentThread().getName());
            logger.info("returnType:" + method.getReturnType());
        }
        logger.info(client.result());
        return client.result();
    }


}
