package cn.solinx.forest.rpc.api;

/**
 * Created by linx on 2015/4/26.
 */
public abstract class AbstractInvoker implements Invoker {

    private Class<?> interfaceName;
    private String address;

    @Override
    public void invoke() {

    }

    public Class<?> getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(Class<?> interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
