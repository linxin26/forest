package co.solinx.forest.rpc;


import cn.solinx.forest.rpc.api.AbstractInvoker;
import cn.solinx.forest.rpc.api.Invocation;
import co.solinx.forest.common.extension.ExtensionLoader;
import co.solinx.forest.registry.api.AbstractRegistry;

/**
 * Created by linx on 2015/4/26.
 */
public class ForestInvoker extends AbstractInvoker {

    private AbstractRegistry registry = new ExtensionLoader<AbstractRegistry>().loadExtension(AbstractRegistry.class);

    public ForestInvoker(String interfaceName,String registryAddres) {

        try {
            this.setInterfaceName(Class.forName(interfaceName).getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //服务提供者地址
        String address = registry.getServer(interfaceName,registryAddres);
        this.setAddress(address);
        //注册消费者
        registry.registryConsumer(interfaceName);


    }

    @Override
    public Object invoke(Invocation invocation) throws Exception {
        return super.invoke(invocation);
    }


}
