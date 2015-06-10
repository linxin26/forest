package co.solinx.forest.config.spring.schema;

import co.solinx.forest.config.ProtocolConfig;
import co.solinx.forest.config.RegistryConfig;
import co.solinx.forest.config.spring.ReferenceBean;
import co.solinx.forest.config.spring.ServiceBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.lang.reflect.Method;

/**
 * Spring自定义标签解析类
 * Created by LX on 2015/3/3.
 */
public class ForestBeanDefinitionParser implements BeanDefinitionParser {

    private Logger logger = Logger.getLogger(ForestBeanDefinitionParser.class);
    private Class<?> beanClass;
    private boolean required;
//    private Map<String,ServiceConfig> serviceConfigMap=new HashMap<String, ServiceConfig>();

    public ForestBeanDefinitionParser(Class<?> beanClass, boolean required) {
        this.beanClass = beanClass;
        this.required = required;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String id = null;

        //服务暴露bean
        if (ServiceBean.class.equals(beanClass)) {
            id = element.getAttribute("interface");
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }

        //服务引用bean
        if (ReferenceBean.class.equals(beanClass)) {
            id = element.getAttribute("id");
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }

        //注册中心配置
        if (RegistryConfig.class.equals(beanClass)) {
            id = "registryAddress";
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }
        if (ProtocolConfig.class.equals(beanClass)) {
            id = element.getAttribute("name");
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        }

        if ((id == null || id == "") & required) {
            id = beanClass.getSimpleName();
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }

        if (ServiceBean.class.equals(beanClass)) {

            beanDefinition.getPropertyValues().addPropertyValue("interfaceName", element.getAttribute("interface"));
            beanDefinition.getPropertyValues().addPropertyValue("ref", element.getAttribute("ref"));
            beanDefinition.getPropertyValues().addPropertyValue("protocol", element.getAttribute("protocol"));
        } else if (RegistryConfig.class.equals(beanClass)) {
            beanDefinition.getPropertyValues().addPropertyValue("address", element.getAttribute("address"));
        } else if (ReferenceBean.class.equals(beanClass)) {
            beanDefinition.getPropertyValues().addPropertyValue("interfaceName", element.getAttribute("interface"));

            if(element.hasAttribute("async")){
                beanDefinition.getPropertyValues().addPropertyValue("async",element.getAttribute("async"));
            }

        } else if (ProtocolConfig.class.equals(beanClass)) {
            beanDefinition.getPropertyValues().addPropertyValue("port", element.getAttribute("port"));
        }

        for (Method setter : beanClass.getMethods()) {
            String name = setter.getName();
            logger.info(name);
        }
        return beanDefinition;
    }
}
