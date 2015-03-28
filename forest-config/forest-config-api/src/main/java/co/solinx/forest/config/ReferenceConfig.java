package co.solinx.forest.config;

import co.solinx.forest.common.utils.InetAddressUtils;
import co.solinx.forest.registry.zookeeper.ZookeeperRegistry;
import co.solinx.forest.remote.proxy.DefaultProxy;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.List;

/**
 * 引用配置类
 * Created by LX on 2015/3/9.
 */
public class ReferenceConfig<T> extends AbstractConfig {

    Logger logger = Logger.getLogger(ReferenceConfig.class);

    public String id;
    public String name;
    public String interfaceName;
    public T ref;
    public String protocol;
    private ApplicationContext context;

    public T get(ApplicationContext context) {

        logger.info(interfaceName);
//        if (ref == null) {
//            init();
//        }
        this.context = context;
        init();
        return ref;
    }

    /**
     * 初始化接口代理
     */
    public void init() {
        //代理类
        DefaultProxy proxy = new DefaultProxy();
        try {
            //注册中心
            RegistryConfig registryCenter = (RegistryConfig) context.getBean("registryAddress");
            ZookeeperRegistry zookeeperRegistry = ZookeeperRegistry.getZookeeper();
            zookeeperRegistry.toRegistry(registryCenter.getAddress());   //连接注册中心
            //取得注册的服务
            List<String> impl = zookeeperRegistry.getServiceImplList(interfaceName);

            String serviceImpl = URLDecoder.decode(impl.get(0), "UTF-8");
            String serverAddress = serviceImpl.substring(serviceImpl.indexOf("//") + 2, serviceImpl.lastIndexOf("/"));

            ref = (T) proxy.proxy(Class.forName(interfaceName), serverAddress);
            String serviceApiNote=zookeeperRegistry.ROOT_NOTE + "/" + interfaceName;
            String concumersNote=zookeeperRegistry.ROOT_NOTE + "/" + interfaceName + "/" + zookeeperRegistry.CONSUMERS_NOTE;
            String currentConsumer=zookeeperRegistry.ROOT_NOTE + "/" + interfaceName + "/" + zookeeperRegistry.CONSUMERS_NOTE + "/" + InetAddressUtils.findAddress();
            zookeeperRegistry.registerService(serviceApiNote);
            zookeeperRegistry.registerService(concumersNote);
            zookeeperRegistry.registerService(currentConsumer);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    @Override
    public String toString() {
        return "ReferenceConfig{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", ref='" + ref + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
