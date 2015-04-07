package co.solinx.forest.rpc.protocol.handler;

import co.solinx.forest.remote.invoker.NettyInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 鑫 on 2015/4/7.
 */
public class JdkProxyHandler implements InvocationHandler {
    private Object delegate;
    private String serverAddress;



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        NettyInvoker invoker=new NettyInvoker();

        return invoker.clientInvoker(delegate,serverAddress,method);
    }

    public JdkProxyHandler(Object delegate, String serverAddress) {
        this.delegate = delegate;
        this.serverAddress = serverAddress;
    }
}
