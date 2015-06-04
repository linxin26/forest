package co.solinx.forest.demo;

import cn.solinx.forest.rpc.api.RpcContext;
import co.solinx.forest.demo.api.IHelloForestService;
import co.solinx.forest.demo.api.IHelloRpcService;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutionException;

/**
 * Created by LX on 2015/3/18.
 */
public class main {
    Logger logger = Logger.getLogger(main.class);

    private IHelloRpcService rpcService;
    private IHelloForestService forestService;

    public IHelloRpcService getRpcService() {
        return rpcService;
    }

    public void setRpcService(IHelloRpcService rpcService) {
        this.rpcService = rpcService;
    }

    public void setForestService(IHelloForestService forestService) {
        this.forestService = forestService;
    }

    public void start() {
//        for (int i = 0; i < 100; i++) {
//            logger.info(rpcService.print());
//        }
        rpcService.hello();
//        forestService.hello();
        for (int i = 0; i < 10; i++) {
            //异步
//            try {
//               System.out.println(RpcContext.getContext().getFuture().get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
            logger.info(forestService.print(String.valueOf(i)));

        }


    }
}
