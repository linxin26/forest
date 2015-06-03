package co.solinx.forest.rpc.jdk;

import cn.solinx.forest.rpc.api.Invocation;
import cn.solinx.forest.rpc.api.Invoker;

/**
 * Created by 鑫 on 2015/4/7.
 */
public abstract class AbstractProxy {

    public abstract Object createProxy(Class interfaceClass, String address);

    public abstract Object createProxy(Invoker invoke,Class interfaces,Invocation invocation);


}
