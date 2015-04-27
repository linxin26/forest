package co.solinx.forest.rpc.jdk.handler;

import cn.solinx.forest.rpc.api.AbstractInvoker;
import co.solinx.forest.remote.invoker.NettyInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 鑫 on 2015/4/7.
 */
public class JdkProxyHandler implements InvocationHandler {
    private Object delegate;
    private String serverAddress;
    private AbstractInvoker invoker;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        NettyInvoker nettyInvoker = new NettyInvoker();
        return nettyInvoker.clientInvoker(invoker.getInterfaceName().getName(), invoker.getAddress(), method, args);
    }

    public JdkProxyHandler(Object delegate, String serverAddress) {
        this.delegate = delegate;
        this.serverAddress = serverAddress;
    }

    public JdkProxyHandler(AbstractInvoker invoke) {
        this.invoker = invoke;
    }
}
