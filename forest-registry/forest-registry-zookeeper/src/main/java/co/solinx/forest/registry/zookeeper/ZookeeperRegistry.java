package co.solinx.forest.registry.zookeeper;

import co.solinx.forest.registry.IRegistry;
import co.solinx.remote.zookeeper.ZookeeperClient;

/**
 * Created by LX on 2015/3/10.
 */
public class ZookeeperRegistry implements IRegistry {

    @Override
    public void toRegistry(String url) {
        ZookeeperClient client = new ZookeeperClient(url);

    }
}
