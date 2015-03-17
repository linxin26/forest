package co.solinx.forest.config.spring.schema;

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

        if (ServiceBean.class.equals(beanClass)) {
            id = element.getAttribute("interface");
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }
        if (ReferenceBean.class.equals(beanClass)) {
            id = element.getAttribute("interface");
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }

        if ((id == null || id == "") & required) {
//            id = element.getAttribute("id");
//            String name = element.getAttribute("name");
            id = beanClass.getSimpleName();
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }

        if (ServiceBean.class.equals(beanClass)) {
//            String className = element.getAttribute("class");
//            if (className != null&&className.length()>0) {
//                RootBeanDefinition classDefinition = new RootBeanDefinition();
//                classDefinition.setBeanClass(ReflectUtils.forName(className));
//                classDefinition.setLazyInit(false);
//                beanDefinition.getPropertyValues().addPropertyValue("ref", new BeanDefinitionHolder(classDefinition, id + "Impl"));
//            }

            beanDefinition.getPropertyValues().addPropertyValue("interfaceName", element.getAttribute("interface"));
            beanDefinition.getPropertyValues().addPropertyValue("ref", element.getAttribute("ref"));
            beanDefinition.getPropertyValues().addPropertyValue("protocol", element.getAttribute("protocol"));
        } else if (RegistryConfig.class.equals(beanClass)) {
            beanDefinition.getPropertyValues().addPropertyValue("address", element.getAttribute("address"));
        } else if (ReferenceBean.class.equals(beanClass)) {
            beanDefinition.getPropertyValues().addPropertyValue("interfaceName", element.getAttribute("interface"));
        }
        for (Method setter : beanClass.getMethods()) {
            String name = setter.getName();
//            logger.info(name);
        }
        return beanDefinition;
    }
}
