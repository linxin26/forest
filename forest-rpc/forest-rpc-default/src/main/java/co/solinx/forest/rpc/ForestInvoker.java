package co.solinx.forest.rpc;


import cn.solinx.forest.rpc.api.AbstractInvoker;
import cn.solinx.forest.rpc.api.Invocation;

/**
 * Created by linx on 2015/4/26.
 */
public class ForestInvoker extends AbstractInvoker {


    @Override
    public Object invoke(Invocation invocation) {
        return super.invoke(invocation);
    }


    public void initInvoke(Class interfaceName, String address) {
        super.setInterfaceName(interfaceName.getName());
        super.setAddress(address);
    }


}
