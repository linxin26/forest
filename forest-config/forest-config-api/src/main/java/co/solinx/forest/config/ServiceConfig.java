package co.solinx.forest.config;

import co.solinx.forest.registry.zookeeper.ZookeeperRegistry;
import co.solinx.forest.remote.netty.server.NettyServer;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * Created by LX on 2015/3/9.
 */
public class ServiceConfig<T> extends AbstractConfig {

    Logger logger = Logger.getLogger(ServiceConfig.class);

    public String id;
    public String name;
    public String interfaceName;
    public T ref;
    public String protocol;
    ZookeeperRegistry zookeeperRegistry = ZookeeperRegistry.getZookeeper();

    public void Export(ApplicationContext context) {
        logger.info("Export：" + interfaceName);
//            RegistryConfig registryConfig = (RegistryConfig) context.getBean("RegistryConfig");
        zookeeperRegistry.toRegistry("192.168.254.144:2181");
        String[] beanNames = context.getBeanDefinitionNames();
        String root = "forest/";
        for (int i = 0; i < beanNames.length; i++) {

            if (beanNames[i].indexOf(".") != -1) {
                String serviceApi = root + beanNames[i];
                zookeeperRegistry.registerService(serviceApi);
                zookeeperRegistry.registerService(serviceApi + "/providers");
                ServiceConfig serviceConfig = (ServiceConfig) context.getBean(beanNames[i]);
                Object serviceImpl = context.getBean(serviceConfig.getRef().toString());
                zookeeperRegistry.registerService(serviceApi + "/providers/" + serviceImpl.getClass().getName());

            }
        }
            NettyServer server=new NettyServer();
            server.start();
    }
//        else {
//            String[] beanNames = context.getBeanDefinitionNames();
//            for (int i = 0; i < beanNames.length; i++) {
//                if(beanNames[i].equals("ReferenceConfig")) {
//                    ReferenceConfig referenceConfig = (ReferenceConfig) context.getBean(beanNames[i]);
//                    logger.info(referenceConfig);
//                }
//                logger.info(beanNames[i]);
//            }
//            NettyClient client=new NettyClient();
//            client.start();
//        }


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
