package co.solinx.forest.common.extension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by LX on 2015/3/1.
 */
public class ExtensionLoader<T> {

    private static final String SERVICE_DIRECTORY = "META-INF/services/";
    private static final String SERVICE_PROXY_INTERFACE="co.solinx.forest.rpc.jdk.AbstractProxy";

    private final ConcurrentMap<String, Class> cacheClass = new ConcurrentHashMap<String, Class>();

    public T loadExtension(String api,Class inter){
        this.loadFile(api);
        ServiceLoader<T> service= ServiceLoader.load(inter);
        return service.iterator().next();
    }

    public Iterator<T> loadExtensionIterator(String api,Class inter){
        this.loadFile(api);
        ServiceLoader<T> service= ServiceLoader.load(inter);
        return service.iterator();
    }

    public void loadFile(String path) {
        ClassLoader classLoader = findClassLoader();
//        String fileName = SERVICE_DIRECTORY + "co.solinx.forest.container.IContainer";
//        String fileName = SERVICE_DIRECTORY + "co.solinx.forest.rpc.jdk.AbstractProxy";
        String fileName=SERVICE_DIRECTORY+path;
        Enumeration<URL> urls;
        List<Class> classList = new ArrayList();
        try {
            urls = classLoader.getResources(fileName);
            System.out.println("classLoader:" + classLoader);
            System.out.println("urls:" + urls.hasMoreElements());
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));

                String line = "";
                while ((line = reader.readLine()) != null) {

                    Class<?> clazz = classLoader.loadClass(line);
                    cacheClass.put(clazz.getSimpleName(), clazz);
//                    classList.add(clazz);
                    System.out.println(line);
                    System.out.println(clazz.getSimpleName());

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class findClass(String name) {
        Class retval = null;
        if (name != null) {
            retval =cacheClass.get(name);

        }
        return retval;
    }

    private ClassLoader findClassLoader() {
        return ExtensionLoader.class.getClassLoader();
    }

}
