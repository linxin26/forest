/**
 * Created by LX on 2015/3/1.
 */
public class provider {

    public static void main(String[] args) {
        try {
            co.solinx.forest.container.Main.main(args);

//            ApplicationContext context = SpringContainer.context;
//            System.out.println(((RegistryConfig) context.getBean("RegistryConfig")).toString());

//            IHelloForestService service = new HelloForestServiceImpl();
//            Client.export(service, 1234);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
