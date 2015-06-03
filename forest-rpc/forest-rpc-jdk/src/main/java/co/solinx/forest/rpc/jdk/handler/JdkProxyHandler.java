package co.solinx.forest.rpc.jdk.handler;

import cn.solinx.forest.rpc.api.Invocation;
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
    private Invocation invocation;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("toString")) {
            return invoker.toString();
        }
        invocation.setParameters(args);
        invocation.setMethod(method);
        return invoker.invoke(invocation);
    }

    public JdkProxyHandler(Object delegate, String serverAddress) {
        this.delegate = delegate;
        this.serverAddress = serverAddress;
    }



    public JdkProxyHandler(Invoker invoke,Invocation invocation) {
        this.invoker = invoke;
        this.invocation=invocation;
    }
}
