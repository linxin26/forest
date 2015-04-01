package co.solinx.forest.config.spring.schema;

import co.solinx.forest.config.ApplicationConfig;
import co.solinx.forest.config.ProtocolConfig;
import co.solinx.forest.config.RegistryConfig;
import co.solinx.forest.config.spring.ReferenceBean;
import co.solinx.forest.config.spring.ServiceBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Spring自定义标签处理类
 * Created by LX on 2015/3/2.
 */
public class ForestNamespaceHandler extends NamespaceHandlerSupport {

    public ForestNamespaceHandler() {
    }

    @Override
    public void init() {
        registerBeanDefinitionParser("registry", new ForestBeanDefinitionParser(RegistryConfig.class, true));
        registerBeanDefinitionParser("reference", new ForestBeanDefinitionParser(ReferenceBean.class, true));
        registerBeanDefinitionParser("application", new ForestBeanDefinitionParser(ApplicationConfig.class, true));
        registerBeanDefinitionParser("service", new ForestBeanDefinitionParser(ServiceBean.class, true));
        registerBeanDefinitionParser("protocol", new ForestBeanDefinitionParser(ProtocolConfig.class, true));
    }
}
