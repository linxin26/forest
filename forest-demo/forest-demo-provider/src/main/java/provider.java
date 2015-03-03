/**
 * Created by LX on 2015/3/1.
 */
public class provider {

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
