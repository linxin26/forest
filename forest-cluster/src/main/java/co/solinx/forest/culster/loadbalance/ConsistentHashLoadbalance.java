package co.solinx.forest.culster.loadbalance;

import cn.solinx.forest.rpc.api.Invocation;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by linx on 2015-06-15.
 * 一致性哈希算法
 */
public class ConsistentHashLoadbalance implements Loadbalance {

    Logger logger=Logger.getLogger(ConsistentHashLoadbalance.class);

    @Override
    public String select(List<String> providerList,Invocation invocation,String selected) {

        logger.info("---------------------ConsistentHansh");


        for (String temp : providerList){

        }
        System.identityHashCode(invocation);

        return null;
    }
}
