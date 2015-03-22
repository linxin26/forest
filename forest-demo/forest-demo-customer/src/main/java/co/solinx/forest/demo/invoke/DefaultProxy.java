package co.solinx.forest.demo.invoke;

import java.lang.reflect.Proxy;

/**
 * Created by LX on 2015/3/21.
 */
public class DefaultProxy {

    public Object proxy(Class interfaceClass) {
        Object obj = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new DefaultInvocationHandler(interfaceClass.getName()));
        return obj;
    }
}
