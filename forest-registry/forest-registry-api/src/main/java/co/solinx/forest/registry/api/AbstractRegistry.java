package co.solinx.forest.registry.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by linx on 2015-05-08.
 */
public abstract class AbstractRegistry {

    /**
     * 取得服务提供者地址
     *
     * @param serviceImpl
     * @param address
     * @return
     */
    public String getServer(String serviceImpl, String address) {
        String service = null;
        try {
            this.toRegistry(address);   //连接注册中心
            //取得注册的服务
            List<String> impl = this.getServiceImplList(serviceImpl);

            //引用提供的第一个服务

            service = URLDecoder.decode(impl.get(0), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String serverAddress = service.substring(service.indexOf("//") + 2, service.lastIndexOf("/"));
        return serverAddress;
    }

    public abstract void toRegistry(String address);

    public abstract List<String> getServiceImplList(String serviceImpl) throws Exception;

    public abstract void registryConsumer(String serviceApi);

}
