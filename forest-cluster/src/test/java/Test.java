import co.solinx.forest.common.extension.ExtensionLoader;
import co.solinx.forest.culster.loadbalance.Loadbalance;

import java.util.Iterator;
import java.util.Random;
import java.util.ServiceLoader;

/**
 * Created by linx on 2015-05-07.
 */
public class Test {

    public static void main(String[] args){
        Random random =new Random();
        System.out.println(random.nextInt(2));

        /**
         * 加载SPI配置文件
         */
        ExtensionLoader loader = new ExtensionLoader();
        loader.loadFile("co.solinx.forest.container.IContainer");
        ServiceLoader<Loadbalance> service = ServiceLoader.load(Loadbalance.class);
        Iterator<Loadbalance> container = service.iterator();
        System.out.println(container.hasNext());
    }

}
