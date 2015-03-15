package co.solinx.forest.demo.customer;

/**
 * Created by LX on 2015/3/15.
 */
public class customer {

    public static void main(String[] args) {
        try {
            co.solinx.forest.container.Main.main(args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
