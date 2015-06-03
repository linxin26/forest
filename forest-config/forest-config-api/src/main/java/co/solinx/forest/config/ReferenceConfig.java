package co.solinx.forest.config;

import cn.solinx.forest.rpc.api.Invoker;
import cn.solinx.forest.rpc.api.RpcInvocation;
import co.solinx.forest.common.extension.ExtensionLoader;
import co.solinx.forest.culster.support.CulsterInvoker;
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
    private boolean async;
    private AbstractProxy proxy = new ExtensionLoader<AbstractProxy>().loadExtension(AbstractProxy.class);


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
        try {
            //注册中心配置
            RegistryConfig registryCenter = (RegistryConfig) context.getBean("registryAddress");
//            ForestInvoker invoker = new ForestInvoker(interfaceName,registryCenter.getAddress());
            Invoker invoker=new CulsterInvoker(interfaceName,registryCenter.getAddress());

            RpcInvocation invocation=new RpcInvocation();
            invocation.setAsync(async);
            logger.info("-------------------------------异步："+async);
            ref = (T) proxy.createProxy(invoker, Class.forName(interfaceName),invocation);

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

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
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
