package co.solinx.forest.container.spring;

import co.solinx.forest.container.IContainer;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by LX on 2015/2/28.
 */
public class Main {

    public static void main(String[] args) {

        ServiceLoader<IContainer> service = ServiceLoader.load(IContainer.class);

        Iterator<IContainer> container = service.iterator();
        while (container.hasNext()) {
            IContainer modl = container.next();
            modl.start();
        }


    }

}
