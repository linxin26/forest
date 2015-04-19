package co.solinx.forest.remote.invoker;

import co.solinx.forest.remote.netty.client.NettyClient;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by LX on 2015/4/4.
 */
public class NettyInvoker {

    Logger logger = Logger.getLogger(NettyInvoker.class);

    public Object clientInvoker(Object delegate, String serverAddress, Method method,Object[] params) throws InterruptedException {
        NettyClient client = new NettyClient(delegate, method,params);
        if(method.getName()!="toString") {
        client.start(serverAddress);
        logger.info("threadName:" + Thread.currentThread().getName());
        logger.info("returnType:" + method.getReturnType());

//            if(method.getReturnType().getName()!="void") {
//                while (true) {
//                    Object result=client.result();
//                    logger.info(result);
//                    if ( result!= null) {
//                        client.close();
//                        break;
//                    }
//                }
//            }
        }
// else{
//            client.close();
//        }
        logger.info(client.result());
        return client.result();
    }


}
