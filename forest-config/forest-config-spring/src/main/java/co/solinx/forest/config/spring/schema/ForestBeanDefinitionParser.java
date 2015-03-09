package co.solinx.forest.config.spring.schema;

import co.solinx.forest.config.ServiceConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.lang.reflect.Method;

/**
 * Created by LX on 2015/3/3.
 */
public class ForestBeanDefinitionParser implements BeanDefinitionParser {

    private Logger logger = Logger.getLogger(ForestBeanDefinitionParser.class);
    private Class<?> beanClass;
    private boolean required;

    public ForestBeanDefinitionParser(Class<?> beanClass, boolean required) {
        this.beanClass = beanClass;
        this.required = required;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String id = element.getAttribute("id");
        if ((id == null || id == "") & required) {
//            String name = element.getAttribute("name");
            id = beanClass.getSimpleName();
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        } else {
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }
        if (ServiceConfig.class.equals(beanClass)) {
            beanDefinition.getPropertyValues().addPropertyValue("interfaceApi", element.getAttribute("interface"));
        }
        for (Method setter : beanClass.getMethods()) {
            String name = setter.getName();
//            logger.info(name);
        }
        logger.info(beanDefinition.getPropertyValues().get("interfaceApi"));
        logger.info(beanDefinition.getPropertyValues().get("id"));
        return beanDefinition;
    }
}
