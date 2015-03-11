package co.solinx.remote.zookeeper;

import co.solinx.remote.zookeeper.api.IChildrenListener;
import co.solinx.remote.zookeeper.api.IZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by LX on 2015/3/10.
 */
public class ZookeeperClient implements IZookeeperClient {


    private CuratorFramework client;
    private String url;
    Logger log = Logger.getLogger(ZookeeperClient.class);

    public ZookeeperClient(String url) {
        this.url = url;
        client = CuratorFrameworkFactory.newClient(url, new ExponentialBackoffRetry(1000, 3));
        client.start();
        log.info(client.getState());
    }

    /**
     * 添加节点
     *
     * @param path
     * @param ephemeral
     */
    @Override
    public void create(String path, boolean ephemeral) {
        try {
            if (client.checkExists().forPath(path) == null) {
                client.create().forPath(path);
            } else {
                log.info("节点：" + path + ",已存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    @Override
    public void delete(String path) {

    }

    @Override
    public List<String> getChildren(String path) {
        return null;
    }

    @Override
    public List<String> addChildrenListener(String path, IChildrenListener listener) {
        return null;
    }

    @Override
    public void remoteChildrenListener(String path, IChildrenListener listener) {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void close() {

    }
}
