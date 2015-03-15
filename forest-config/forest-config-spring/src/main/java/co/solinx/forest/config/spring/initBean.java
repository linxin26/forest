package co.solinx.forest.config.spring;

import co.solinx.forest.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

/**
 * Created by LX on 2015/3/15.
 */
public class initBean implements Serializable, DisposableBean, BeanFactoryPostProcessor, BeanPostProcessor, ApplicationContextAware {

    Logger logger = LoggerFactory.getLogger(initBean.class);
    private String id;
    private ApplicationContext applicationContext;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        logger.info("postProcessBeanFactory");
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

//        logger.info(bean.getClass().getName());
//        logger.info(beanName);
        logger.info("postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().getSimpleName().equals("ServiceConfig")){
//            logger.info(((ServiceConfig)bean).getRef());
            logger.info(applicationContext.getBean(((ServiceConfig)bean).getRef()).getClass().getName());
        }

//        logger.info(bean.toString());
//        logger.info(beanName);
//        logger.info("postProcessAfterInitialization");
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
