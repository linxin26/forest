package co.solinx.forest.rpc.jdk.handler;

import cn.solinx.forest.rpc.api.Invoker;
import cn.solinx.forest.rpc.api.RpcInvocation;
import co.solinx.forest.remote.invoker.NettyInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 鑫 on 2015/4/7.
 */
public class JdkProxyHandler implements InvocationHandler {
    private Object delegate;
    private String serverAddress;
    private Invoker invoker;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        NettyInvoker nettyInvoker = new NettyInvoker();
        if (method.getName().equals("toString")) {
            return nettyInvoker.toString();
        }
        return invoker.invoke(new RpcInvocation(method, args));
    }

    public JdkProxyHandler(Object delegate, String serverAddress) {
        this.delegate = delegate;
        this.serverAddress = serverAddress;
    }

    public JdkProxyHandler(Invoker invoke) {
        this.invoker = invoke;
    }
}
