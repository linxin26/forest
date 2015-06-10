package co.solinx.forest.config.spring;

import co.solinx.forest.config.ReferenceConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 引用管理类
 * Created by LX on 2015/3/17.
 */
public class ReferenceBean<T> extends ReferenceConfig<T> implements FactoryBean, ApplicationContextAware, InitializingBean, DisposableBean {

    Logger logger = Logger.getLogger(ReferenceBean.class);

    private ApplicationContext applicationContext;
    Object obj;


    @Override
    public Object getObject() throws Exception {
        //返回bean的代理对象
        return obj;
    }



    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("---------------------------"+interfaceName);
//        getObject();
        //bean 初始化时创建bean的代理
        obj = get(applicationContext);
        logger.info(obj);
    }


    @Override
    public void destroy() throws Exception {

    }
}
