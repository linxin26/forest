package co.solinx.forest.rpc;


import cn.solinx.forest.rpc.api.AbstractInvoker;

/**
 * Created by linx on 2015/4/26.
 */
public class ForestInvoker extends AbstractInvoker {

    //    private String test;



    @Override
    public void invoke() {

    }


    public void initInvoke(Class<?> interfaceName, String address) {
        super.setInterfaceName(interfaceName);
        super.setAddress(address);
    }



}
