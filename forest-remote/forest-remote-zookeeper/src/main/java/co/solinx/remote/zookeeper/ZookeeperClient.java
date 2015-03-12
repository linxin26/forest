package co.solinx.remote.zookeeper;

import co.solinx.remote.zookeeper.api.IChildrenListener;
import co.solinx.remote.zookeeper.api.IZookeeperClient;
import co.solinx.remote.zookeeper.watcher.DefaultWatcher;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.apache.zookeeper.data.Stat;

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
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                log.info("连接状态");
                log.info(connectionState);
            }
        });
        log.info(client.getState());
    }

    /**
     * 添加节点
     *
     * @param note
     * @param ephemeral
     */
    @Override
    public void create(String note, boolean ephemeral) {
        try {
            if (!this.checkExists(note)) {
                client.create().forPath(note);
            } else {
                log.info("节点：" + note + ",已存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    /**
     * 监听Note
     *
     * @param note
     * @throws Exception
     */
    public void watcherNote(String note) throws Exception {
        DefaultWatcher watcher = new DefaultWatcher(client, note);
        watcher.start();
    }

    public void watcherNoteData(String note) {

    }

    /**
     * 检查path是否存在
     *
     * @param note
     * @return
     * @throws Exception
     */
    public boolean checkExists(String note) throws Exception {
        boolean exists = false;
        Stat stat = client.checkExists().forPath(note);
        if (stat != null) {
            exists = true;
        }
        return exists;
    }

    /**
     * 删除Note
     *
     * @param note
     * @throws Exception
     */
    @Override
    public void deleteNote(String note) throws Exception {
        client.delete().forPath(note);
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
