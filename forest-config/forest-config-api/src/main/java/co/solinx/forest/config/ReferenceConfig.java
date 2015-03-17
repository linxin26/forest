package co.solinx.forest.config;

import co.solinx.forest.registry.zookeeper.ZookeeperRegistry;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by LX on 2015/3/9.
 */
public class ReferenceConfig<T> extends AbstractConfig {

    Logger logger = Logger.getLogger(ReferenceConfig.class);

    public String id;
    public String name;
    public String interfaceName;
    public T ref;
    public String protocol;

    public T get() {

        logger.info(interfaceName);
        if (ref == null) {
            init();
        }
        return ref;
    }

    public void init() {

        ZookeeperRegistry zookeeperRegistry = ZookeeperRegistry.getZookeeper();
        zookeeperRegistry.toRegistry("192.168.254.144:2181");

        try {
            List<String> serviceList = zookeeperRegistry.getServiceImplList(interfaceName);
            for (String temp : serviceList) {
                
                logger.info(temp);
            }
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
