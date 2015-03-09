import co.solinx.forest.config.RegistryConfig;
import co.solinx.forest.container.spring.SpringContainer;
import org.springframework.context.ApplicationContext;

/**
 * Created by LX on 2015/3/1.
 */
public class provider {

    public static void main(String[] args) {
        try {
            co.solinx.forest.container.Main.main(args);
           ApplicationContext context= SpringContainer.context;
            System.out.println(context.containsBean("bidService"));
            System.out.println(context.containsBean("ServiceConfig"));
            System.out.println(((RegistryConfig) context.getBean("RegistryConfig")).toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
