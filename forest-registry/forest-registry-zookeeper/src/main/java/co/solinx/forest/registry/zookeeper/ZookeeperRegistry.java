package co.solinx.forest.registry.zookeeper;

import co.solinx.forest.registry.IRegistry;
import co.solinx.remote.zookeeper.ZookeeperClient;
import org.apache.log4j.Logger;

/**
 * Created by LX on 2015/3/10.
 */
public class ZookeeperRegistry implements IRegistry {

    Logger log = Logger.getLogger(ZookeeperRegistry.class);
    ZookeeperClient client;

    @Override
    public void toRegistry(String url) {
        client = new ZookeeperClient(url.replace("zookeeper://", ""));

    }

    public void registryService(String note) {
//        client.create("/forest/co.solinx.forest", true);
        try {
            client.create(note, true);
            client.watcherNote(note);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeService(String note) throws Exception {
        client.deleteNote(note);
    }
}
