package co.solinx.forest.config.spring.schema;

import co.solinx.forest.config.RegistryConfig;
import co.solinx.forest.config.ServiceConfig;
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

    }
}
