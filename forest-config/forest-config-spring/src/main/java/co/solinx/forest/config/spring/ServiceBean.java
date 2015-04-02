package co.solinx.forest.config.spring;

import co.solinx.forest.config.ServiceConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

/**
 * Service管理类
 * Created by LX on 2015/3/15.
 */
public class ServiceBean<T> extends ServiceConfig implements Serializable, DisposableBean, BeanNameAware, ApplicationListener, ApplicationContextAware {

    Logger logger = Logger.getLogger(ServiceBean.class);
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
//        logger.info(applicationEvent.getClass().getName());
        if (ContextRefreshedEvent.class.getName().equals(applicationEvent.getClass().getName())) {
            if (!export) {
                try {
                    logger.info("_________________________"+applicationEvent);
                    Export(applicationContext);
                    export = true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    export = false;
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
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
