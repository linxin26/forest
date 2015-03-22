package co.solinx.forest.config;

import co.solinx.forest.registry.zookeeper.ZookeeperRegistry;
import co.solinx.forest.remote.netty.server.NettyServer;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务配置类
 * Created by LX on 2015/3/9.
 */
public class ServiceConfig<T> extends AbstractConfig {

    Logger logger = Logger.getLogger(ServiceConfig.class);

    public String id;
    public String name;
    public String interfaceName;
    public T ref;
    public String protocol;
    private List<Object> serviceList = new ArrayList<Object>();
    ZookeeperRegistry zookeeperRegistry = ZookeeperRegistry.getZookeeper();

    /**
     * 导出服务
     *
     * @param context
     */
    public void Export(ApplicationContext context) throws ClassNotFoundException {
        logger.info("Export：" + interfaceName);
//            RegistryConfig registryConfig = (RegistryConfig) context.getBean("RegistryConfig");
       //连接注册中心
        zookeeperRegistry.toRegistry("192.168.254.144:2181");
        String[] beanNames = context.getBeanDefinitionNames();
        String root = "forest/";
        //往注册中心注册服务
        for (int i = 0; i < beanNames.length; i++) {

            if (beanNames[i].indexOf(".") != -1) {
                String serviceApi = root + beanNames[i];
                zookeeperRegistry.registerService(serviceApi);
                zookeeperRegistry.registerService(serviceApi + "/providers");
                ServiceConfig serviceConfig = (ServiceConfig) context.getBean(beanNames[i]);
                Object serviceImpl = context.getBean(serviceConfig.getRef().toString());
                serviceList.add(serviceImpl);
                zookeeperRegistry.registerService(serviceApi + "/providers/" + serviceImpl.getClass().getName());

            }
        }
        NettyServer server = NettyServer.getInstance();
        server.open(serviceList);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
