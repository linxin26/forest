package cn.solinx.forest.rpc.api;

import co.solinx.forest.remote.invoker.NettyInvoker;

/**
 * Created by linx on 2015/4/26.
 */
public abstract class AbstractInvoker implements Invoker {

    private String interfaceName;
    private String address;

    @Override
    public Object invoke(Invocation invocation) {
        NettyInvoker nettyInvoker = new NettyInvoker();
        Object result = null;
        try {
            result = nettyInvoker.clientInvoker(interfaceName, address, invocation.getMethod(), invocation.getParameters());
        } catch (InterruptedException e) {

        }
        return result;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
