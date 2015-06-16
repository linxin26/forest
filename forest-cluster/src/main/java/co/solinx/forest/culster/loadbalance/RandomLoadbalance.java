package co.solinx.forest.culster.loadbalance;

import cn.solinx.forest.rpc.api.Invocation;
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
    public String select(List<String> providerList,Invocation invocation,String selected) {
         String address=this.randomSelected(providerList);
        if(providerList.size()>1){
            if(address.equals(selected)){
                while (true){
                    address=this.randomSelected(providerList);
                    if(!this.repeat(address,selected)){
                        break;
                    }
                }

            }
        }
        logger.info("--select------------------"+address);
        return address;
    }

    private boolean repeat(String address,String selected){
        logger.info("--repeat-------------------------"+address+"="+selected);
        boolean result=false;
        if(selected.equals(address)){
            result=true;
        }
        return result;
    }

    private String randomSelected(List<String> providerList){
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
