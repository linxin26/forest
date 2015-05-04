package co.solinx.forest.container;

import co.solinx.forest.common.extension.ExtensionLoader;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by LX on 2015/2/28.
 */
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        /**
         * 加载SPI配置文件
         */
        ExtensionLoader<IContainer> loader = new ExtensionLoader();
//        loader.loadFile("co.solinx.forest.container.IContainer");
//        loader.loadExtension("co.solinx.forest.container.IContainer",IContainer.class);
        Iterator<IContainer> container = loader.loadExtensionIterator("co.solinx.forest.container.IContainer", IContainer.class);
//        ServiceLoader<IContainer> service = ServiceLoader.load(IContainer.class);
//        Iterator<IContainer> container = service.iterator();
        /**
         * 启动容器
         */
        while (container.hasNext()) {
            IContainer modl = container.next();
            modl.start();
        }


    }

}
