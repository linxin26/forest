package co.solinx.forest.config;

import co.solinx.forest.registry.zookeeper.ZookeeperRegistry;
import co.solinx.forest.remote.netty.server.NettyServer;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务配置类
 * Created by LX on 2015/3/9.
 */
public class ServiceConfig<T> extends AbstractConfig {

    Logger logger = Logger.getLogger(ServiceConfig.class);

    private static int num;
    public String id;
    public String name;
    public String interfaceName;
    public T ref;
    public String protocol;
    private static List<Object> serviceList = new ArrayList<Object>();
    ZookeeperRegistry zookeeperRegistry = ZookeeperRegistry.getZookeeper();

    public ServiceConfig() {
        num++;
    }

    /**
     * 导出服务
     *
     * @param context
     */
    public void Export(ApplicationContext context) throws ClassNotFoundException, UnknownHostException, UnsupportedEncodingException {
        logger.info("Export：" + interfaceName);
        RegistryConfig registryAddress = (RegistryConfig) context.getBean("registryAddress");
        //连接注册中心
        zookeeperRegistry.toRegistry(registryAddress.getAddress());
        //往注册中心注册服务
        logger.info("interfaceName_______________" + interfaceName);
        String serviceApi = interfaceName;

        ProtocolConfig protocol= (ProtocolConfig) context.getBean("forest");
        //取得服务
        ServiceConfig serviceConfig = (ServiceConfig) context.getBean(serviceApi);
        Object serviceImpl = context.getBean(serviceConfig.getRef().toString());
        serviceList.add(serviceImpl);
        //注册服务
        zookeeperRegistry.registryService(serviceImpl.getClass().getName(),serviceApi,protocol.getPort());
        logger.info(serviceList);

        //加载所有服务后启动Server
        if (serviceList.size() == num) {

            this.startServer(protocol.getPort());
        }
    }

    public void startServer(int port) {

        NettyServer server = NettyServer.getInstance();
        server.open(serviceList,port);
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
