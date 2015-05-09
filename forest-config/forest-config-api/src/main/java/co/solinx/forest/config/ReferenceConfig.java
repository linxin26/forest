package co.solinx.forest.config;

import co.solinx.forest.common.extension.ExtensionLoader;
import co.solinx.forest.registry.api.AbstractRegistry;
import co.solinx.forest.rpc.ForestInvoker;
import co.solinx.forest.rpc.jdk.AbstractProxy;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

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
    private ExtensionLoader<AbstractProxy> extensionLoader = new ExtensionLoader();
    private AbstractProxy proxy = extensionLoader.loadExtension("co.solinx.forest.rpc.jdk.AbstractProxy", AbstractProxy.class);
    private ExtensionLoader<AbstractRegistry> loader = new ExtensionLoader();
    AbstractRegistry registry =loader.loadExtension("",AbstractRegistry.class);


    public ReferenceConfig() {
        logger.info("constructor-----------");
    }

    public T get(ApplicationContext context) {

        logger.info(interfaceName);
        this.context = context;
        init();
        return ref;
    }

    /**
     * 初始化接口代理
     */
    public void init() {
        //代理类
//        DefaultProxy proxy = new DefaultProxy();


        try {

//            AbstractRegistry registry = ZookeeperRegistry.getZookeeper();
            //注册中心配置
            RegistryConfig registryCenter = (RegistryConfig) context.getBean("registryAddress");
            //服务提供者地址
            String address = registry.getServer(interfaceName, registryCenter.getAddress());

            ForestInvoker invoker = new ForestInvoker();
            invoker.initInvoke(Class.forName(interfaceName), address);
            ref = (T) proxy.createProxy(invoker, Class.forName(interfaceName));
            //注册消费者
            registry.registryConsumer(interfaceName);
        } catch (ClassNotFoundException e) {
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
