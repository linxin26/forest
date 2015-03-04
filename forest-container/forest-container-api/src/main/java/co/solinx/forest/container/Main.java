package co.solinx.forest.container;

import co.solinx.forest.common.extension.ExtensionLoader;

/**
 * Created by LX on 2015/2/28.
 */
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {


        ExtensionLoader loader = new ExtensionLoader();
         loader.loadFile();
        IContainer container = (IContainer)loader.findClass("Log4jContainer").newInstance();
        container.start();
//        for (int i = 0; i < classList.size(); i++) {
//            IContainer container = (IContainer) classList.get(i).newInstance();
//            container.start();
//        }
//        List<IContainer> containers = new ArrayList<IContainer>();
//        ServiceLoader<IContainer> service = ServiceLoader.load(IContainer.class);
//        Iterator<IContainer> container = service.iterator();
//        while (container.hasNext()) {
//            IContainer modl = container.next();
//            modl.start();
//        }


    }

}
