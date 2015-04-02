package co.solinx.forest.remote.proxy;

import org.apache.log4j.Logger;

import java.lang.reflect.Proxy;

/**
 * Created by LX on 2015/3/21.
 */
public class DefaultProxy {
    Logger logger=Logger.getLogger(DefaultProxy.class);
    public Object proxy(Class interfaceClass,String serverAddress) {
        logger.info(interfaceClass);
        Object obj = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new DefaultInvocationHandler(interfaceClass.getName(),serverAddress));        return obj;
    }
}
