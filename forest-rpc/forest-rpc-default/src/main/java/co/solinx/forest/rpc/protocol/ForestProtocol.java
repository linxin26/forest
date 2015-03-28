package co.solinx.forest.rpc.protocol;

import co.solinx.forest.config.RegistryConfig;
import co.solinx.forest.registry.zookeeper.ZookeeperRegistry;
import org.apache.log4j.Logger;

/**
 * Created by LX on 2015/3/16.
 */
public class ForestProtocol {

    Logger logger=Logger.getLogger(ForestProtocol.class);

    ZookeeperRegistry zookeeperRegistry = ZookeeperRegistry.getZookeeper();

    public void export(String interfaceName,RegistryConfig registryAddress) {
        //forest://192.168.254.144:20909/co.solinx.forest.demo.impl.HelloForestServiceImpl
//        String forestProtocol="forest://";
//
//        //连接注册中心
//        zookeeperRegistry.toRegistry(registryAddress.getAddress());
//        //往注册中心注册服务
//        logger.info("interfaceName_______________" + interfaceName);
//        String serviceApi = interfaceName;
//        zookeeperRegistry.registryServerApi(serviceApi);
//        zookeeperRegistry.registerService(zookeeperRegistry.ROOT_NOTE + "/" + serviceApi + "/" + ZookeeperRegistry.PROVIDRES_NOTE);
//        ServiceConfig serviceConfig = (ServiceConfig) context.getBean(serviceApi);
//        Object serviceImpl = context.getBean(serviceConfig.getRef().toString());
//        serviceList.add(serviceImpl);
//        zookeeperRegistry.registerService(zookeeperRegistry.ROOT_NOTE + "/" + serviceApi + "/" + ZookeeperRegistry.PROVIDRES_NOTE + "/" + serviceImpl.getClass().getName());
//        logger.info(serviceList);
//
//        //加载所有服务后启动Server
//        if (serviceList.size() == num) {
//            this.startServer();
//        }

    }

}
