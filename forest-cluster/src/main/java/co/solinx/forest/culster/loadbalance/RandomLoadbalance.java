package co.solinx.forest.culster.loadbalance;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Random;

/**
 * Created by linx on 2015-05-10.
 */
public class RandomLoadbalance implements Loadbalance {

    Logger logger=Logger.getLogger(RandomLoadbalance.class);
    private Random random = new Random();

    @Override
    public String select(List<String> providerList) {

        String service = null;
        String serverAddress = null;
        try {
            service = URLDecoder.decode(providerList.get(random.nextInt(providerList.size())), "UTF-8");
            serverAddress = service.substring(service.indexOf("//") + 2, service.lastIndexOf("/"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return serverAddress;
    }
}
