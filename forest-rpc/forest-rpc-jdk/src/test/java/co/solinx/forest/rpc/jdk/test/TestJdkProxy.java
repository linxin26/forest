package co.solinx.forest.rpc.jdk.test;

import co.solinx.forest.common.extension.ExtensionLoader;
import co.solinx.forest.rpc.jdk.AbstractProxy;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by linx on 2015-05-04.
 */
public class TestJdkProxy {

    public static void main(String[] args){

        /**
         * 加载SPI配置文件
         */
        ExtensionLoader loader = new ExtensionLoader();
        loader.loadFile("co.solinx.forest.container.IContainer");
        ServiceLoader<AbstractProxy> service = ServiceLoader.load(AbstractProxy.class);
        Iterator<AbstractProxy> container = service.iterator();


    }
}
