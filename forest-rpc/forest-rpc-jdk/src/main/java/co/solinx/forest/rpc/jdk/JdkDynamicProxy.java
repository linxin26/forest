package co.solinx.forest.rpc.jdk;

import co.solinx.forest.rpc.jdk.handler.JdkProxyHandler;

import java.lang.reflect.Proxy;

/**
 * Created by 鑫 on 2015/4/7.
 */
public class JdkDynamicProxy extends AbstractProxy{
    @Override
    public Object createProxy(Class interfaceClass, String serverAddress) {
        return Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new JdkProxyHandler(interfaceClass.getName(), serverAddress));

    }
}
