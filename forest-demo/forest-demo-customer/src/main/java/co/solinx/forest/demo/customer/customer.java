package co.solinx.forest.demo.customer;

/**
 * Created by LX on 2015/3/15.
 */
public class customer {

    public static void main(String[] args) {
        try {
            co.solinx.forest.container.Main.main(args);

//            IHelloForestService service = Client.refer(IHelloForestService.class, "127.0.0.1", 1234);
//            service.hello();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
