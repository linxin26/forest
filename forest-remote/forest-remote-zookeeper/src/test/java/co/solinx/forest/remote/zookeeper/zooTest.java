package co.solinx.forest.remote.zookeeper;

import co.solinx.remote.zookeeper.ZookeeperClient;

/**
 * Created by LX on 2015/3/12.
 */
public class zooTest {
    public static void main(String[] args) {
        try {
            ZookeeperClient client = new ZookeeperClient("192.168.254.144");

            client.create("/test", true);
            client.watcherNote("/test");
            client.deleteNote("/test");

//            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
