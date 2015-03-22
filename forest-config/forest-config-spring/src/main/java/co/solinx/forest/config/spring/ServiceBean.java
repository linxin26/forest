package co.solinx.forest.config.spring;

import co.solinx.forest.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.Serializable;

/**
 * Service管理类
 * Created by LX on 2015/3/15.
 */
public class ServiceBean<T> extends ServiceConfig implements Serializable, DisposableBean, BeanNameAware, ApplicationListener, ApplicationContextAware {

    Logger logger = LoggerFactory.getLogger(ServiceBean.class);
    private String beanName;
    private ApplicationContext applicationContext;
    private boolean export;


    public ServiceBean() {
        logger.info("ServiceBean ");
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        logger.info(applicationEvent.getClass().getName());
        if (ContextRefreshedEvent.class.getName().equals(applicationEvent.getClass().getName())) {
            if (!export) {
                try {
                    Export(applicationContext);
                    export = true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    export = false;
                }

            }
        }

    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
