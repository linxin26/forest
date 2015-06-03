package co.solinx.forest.culster.support;

import cn.solinx.forest.rpc.api.AbstractInvoker;
import cn.solinx.forest.rpc.api.Invocation;
import co.solinx.forest.common.byteCode.LoadClass;
import co.solinx.forest.common.extension.ExtensionLoader;
import co.solinx.forest.culster.loadbalance.Loadbalance;
import co.solinx.forest.registry.api.AbstractRegistry;
import co.solinx.forest.remote.transport.ITransporter;
import co.solinx.forest.remote.transport.NettyTransporter;
import co.solinx.forest.rpc.ForestInvoker;
import org.apache.log4j.Logger;

import java.net.URLDecoder;
import java.util.List;

/**
 * Created by linx on 2015-05-10.
 */
public class CulsterInvoker extends AbstractInvoker {

    Logger logger = Logger.getLogger(CulsterInvoker.class);

    private AbstractRegistry registry = new ExtensionLoader<AbstractRegistry>().loadExtension(AbstractRegistry.class);
    private Loadbalance loadbalance = new ExtensionLoader<Loadbalance>().loadExtension(Loadbalance.class);
    private String interfaceName;
    private ITransporter transporter = new NettyTransporter();
    ;

    private List<String> providerList;

    public CulsterInvoker(String interfaceName, String address) {
        this.interfaceName = interfaceName;

        registry.toRegistry(address);
        //注册消费者
        registry.registryConsumer(interfaceName);
        //服务提供者地址
        providerList = registry.getProviderList(interfaceName);

        //向每个服务提供者发起一个连接
        for (int i = 0; i < providerList.size(); i++) {
            String temp = URLDecoder.decode(providerList.get(i));
            temp = temp.substring(temp.indexOf("//") + 2, temp.lastIndexOf("/"));
            logger.info(temp);
            transporter.open(temp.split(":")[0], Integer.parseInt(temp.split(":")[1]), transporter);
            transporter.connect();
            this.getTransporterList().put(temp, transporter);

        }


    }

    @Override
    public Object invoke(Invocation invocation) throws Exception {
        String address = loadbalance.select(providerList);
        ForestInvoker forestInvoker= new ForestInvoker(LoadClass.getClassName(interfaceName), address);
        forestInvoker.setTransporterList(this.getTransporterList());
        return forestInvoker.invoke(invocation);
    }
}
