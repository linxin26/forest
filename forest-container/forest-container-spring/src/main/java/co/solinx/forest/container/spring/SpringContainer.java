package co.solinx.forest.container.spring;

import co.solinx.forest.config.RegistryConfig;
import co.solinx.forest.config.ServiceConfig;
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
        logger.info(((ServiceConfig) context.getBean("co.solinx.forest.demo.api.IHelloForestService")).getRef());
        logger.info(context.containsBean("RegistryConfig"));

        RegistryConfig registryConfig = (RegistryConfig) context.getBean("RegistryConfig");
        ZookeeperRegistry zookeeperRegistry = new ZookeeperRegistry();
        zookeeperRegistry.toRegistry(registryConfig.getAddress());

        String[] beanNames = context.getBeanDefinitionNames();
        String root = "forest/";
        for (int i = 0; i < beanNames.length; i++) {

            if (beanNames[i].indexOf(".") != -1) {
                String serviceApi = root + beanNames[i];
                zookeeperRegistry.registerService(serviceApi);
                zookeeperRegistry.registerService(serviceApi + "/providers");
                        ServiceConfig serviceConfig = (ServiceConfig) context.getBean(beanNames[i]);
                Object serviceImpl = context.getBean(serviceConfig.getRef());
                zookeeperRegistry.registerService(serviceApi + "/providers/" + serviceImpl.getClass().getName());
//                logger.info(serviceImpl.getClass().getName());
//                logger.info(beanNames[i]);
            }
        }
//        for (int i = 0; i < beanNames.length; i++) {
//            try {
//                if (beanNames[i].indexOf(".") != -1) {
//                    zookeeperRegistry.unRegisterService(root + beanNames[i]);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


    }

    @Override
    public void stop() {
        System.out.println("SpringContainer stop");
    }
}
