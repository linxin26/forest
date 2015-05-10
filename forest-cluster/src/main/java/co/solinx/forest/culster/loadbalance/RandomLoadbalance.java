package co.solinx.forest.culster.loadbalance;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by linx on 2015-05-10.
 */
public class RandomLoadbalance implements Loadbalance{


    @Override
    public String select(List<String> providerList) {

        String service = null;
        String serverAddress = null;
        try {
            service = URLDecoder.decode(providerList.get(0), "UTF-8");

         serverAddress = service.substring(service.indexOf("//") + 2, service.lastIndexOf("/"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return serverAddress;
    }
}
