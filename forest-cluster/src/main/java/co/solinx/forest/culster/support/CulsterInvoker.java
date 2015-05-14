package co.solinx.forest.culster.support;

import cn.solinx.forest.rpc.api.AbstractInvoker;
import cn.solinx.forest.rpc.api.Invocation;
import cn.solinx.forest.rpc.api.Invoker;
import co.solinx.forest.common.extension.ExtensionLoader;
import co.solinx.forest.culster.loadbalance.Loadbalance;
import co.solinx.forest.culster.loadbalance.RandomLoadbalance;
import co.solinx.forest.registry.api.AbstractRegistry;
import co.solinx.forest.remote.transport.ITransporter;
import co.solinx.forest.remote.transport.NettyTransporter;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by linx on 2015-05-10.
 *
 */
public class CulsterInvoker extends AbstractInvoker {

    Logger logger=Logger.getLogger(CulsterInvoker.class);

    private AbstractRegistry registry = new ExtensionLoader<AbstractRegistry>().loadExtension(AbstractRegistry.class);
    private Invoker invoker;
    private String interfaceName;
    private String registryAddress;
    private ITransporter transporter;

    public CulsterInvoker(String interfaceName,String address) {
        this.interfaceName=interfaceName;
        this.registryAddress=address;
        transporter=new NettyTransporter();

        transporter.connect("127.0.0.1");

        registry.toRegistry(address);
        //注册消费者
        registry.registryConsumer(interfaceName);
    }

    @Override
    public Object invoke(Invocation invocation) {

        Loadbalance loadbalance=new RandomLoadbalance();
        //服务提供者地址
        List<String> providerList = registry.getProviderList(interfaceName);

        String address= loadbalance.select(providerList);
        try {
            this.setInterfaceName(Class.forName(interfaceName).getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.setAddress(address);
        return super.invoke(invocation);
    }
}
