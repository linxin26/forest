package co.solinx.forest.demo.netty;

import co.solinx.forest.demo.api.IHelloForestService;
import co.solinx.forest.demo.api.IHelloRpcService;
import co.solinx.forest.demo.invoke.DefaultProxy;

/**
 * Created by LX on 2015/3/21.
 */
public class ClientNetty {

    public static void main(String[] args) {
        DefaultProxy proxy = new DefaultProxy();
        IHelloForestService forestService = (IHelloForestService) proxy.proxy(IHelloForestService.class);
        System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
        System.out.println(4);
        System.out.println("print:" + forestService.print("1"));
        System.out.println("print:" + forestService.print("2"));
        IHelloRpcService rpcService = (IHelloRpcService) proxy.proxy(IHelloRpcService.class);
        System.out.println("print:" + rpcService.print());
        rpcService.hello();
        forestService.hello();
    }

}
