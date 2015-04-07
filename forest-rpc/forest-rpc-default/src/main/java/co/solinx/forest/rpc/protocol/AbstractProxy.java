package co.solinx.forest.rpc.protocol;

/**
 * Created by LX on 2015/4/4.
 */
public abstract class AbstractProxy {

    public abstract Object createProxy(Class interfaceClass,String address);


}
