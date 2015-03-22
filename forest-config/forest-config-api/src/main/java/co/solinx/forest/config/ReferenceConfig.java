package co.solinx.forest.config;

import co.solinx.forest.remote.proxy.DefaultProxy;
import org.apache.log4j.Logger;

/**
 *引用配置类
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
//        if (ref == null) {
//            init();
//        }
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
            ref = (T) proxy.proxy(Class.forName(interfaceName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        ZookeeperRegistry zookeeperRegistry = ZookeeperRegistry.getZookeeper();
//        zookeeperRegistry.toRegistry("192.168.254.144:2181");
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
