package co.solinx.forest.rpc.jdk;

import cn.solinx.forest.rpc.api.AbstractInvoker;

/**
 * Created by 鑫 on 2015/4/7.
 */
public abstract class AbstractProxy {

    public abstract Object createProxy(Class interfaceClass, String address);

    public abstract Object createProxy(AbstractInvoker invoke, Class interfaces);


}
