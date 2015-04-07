package co.solinx.forest.rpc.jdk;

/**
 * Created by 鑫 on 2015/4/7.
 */
public abstract class AbstractProxy {

    public abstract Object createProxy(Class interfaceClass,String address);
}
