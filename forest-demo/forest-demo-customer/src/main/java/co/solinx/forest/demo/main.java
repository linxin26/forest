package co.solinx.forest.demo;

import co.solinx.forest.demo.api.IHelloRpcService;

/**
 * Created by LX on 2015/3/18.
 */
public class main {
    private IHelloRpcService rpcService;

    public IHelloRpcService getRpcService() {
        return rpcService;
    }

    public void setRpcService(IHelloRpcService rpcService) {
        this.rpcService = rpcService;
    }

    public void start() {
        rpcService.hello();
    }
}
