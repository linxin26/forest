package co.solinx.forest.container.spring;

import co.solinx.forest.config.RegistryConfig;
import co.solinx.forest.container.IContainer;
import co.solinx.forest.registry.zookeeper.ZookeeperRegistry;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by LX on 2015/2/28.
 */
public class SpringContainer implements IContainer {

    //打印日志
    Logger logger = Logger.getLogger(SpringContainer.class);

    public static ClassPathXmlApplicationContext context;
    public static final String DEFAULT_SPRING_CONFIG = "classpath*:META-INF/spring/*.xml";

    @Override
    public void start() {

        logger.info("SpringContainer start");
        context = new ClassPathXmlApplicationContext(DEFAULT_SPRING_CONFIG);
        context.start();
        logger.info(context.containsBean("bidService"));
        logger.info(context.containsBean("ServiceConfig"));
        logger.info(context.containsBean("RegistryConfig"));
        RegistryConfig registryConfig = (RegistryConfig) context.getBean("RegistryConfig");
        ZookeeperRegistry zookeeperRegistry = new ZookeeperRegistry();
        zookeeperRegistry.toRegistry(registryConfig.getAddress());
    }

    @Override
    public void stop() {
        System.out.println("SpringContainer stop");
    }
}
