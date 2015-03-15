package co.solinx.forest.config.spring.schema;

import co.solinx.forest.config.ApplicationConfig;
import co.solinx.forest.config.ReferenceConfig;
import co.solinx.forest.config.RegistryConfig;
import co.solinx.forest.config.ServiceConfig;
import co.solinx.forest.config.spring.initBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by LX on 2015/3/2.
 */
public class ForestNamespaceHandler extends NamespaceHandlerSupport {

    public ForestNamespaceHandler() {
    }

    @Override
    public void init() {
        registerBeanDefinitionParser("registry", new ForestBeanDefinitionParser(RegistryConfig.class, true));
        registerBeanDefinitionParser("service", new ForestBeanDefinitionParser(ServiceConfig.class, true));
        registerBeanDefinitionParser("reference", new ForestBeanDefinitionParser(ReferenceConfig.class, true));
        registerBeanDefinitionParser("application", new ForestBeanDefinitionParser(ApplicationConfig.class, true));
        registerBeanDefinitionParser("annotation", new ForestBeanDefinitionParser(initBean.class, true));
    }
}
