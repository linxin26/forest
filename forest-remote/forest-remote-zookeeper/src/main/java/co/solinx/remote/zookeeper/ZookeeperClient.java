package co.solinx.remote.zookeeper;

import co.solinx.remote.zookeeper.api.IChildrenListener;
import co.solinx.remote.zookeeper.api.IZookeeperClient;
import co.solinx.remote.zookeeper.support.ZooNote;
import co.solinx.remote.zookeeper.watcher.DefaultWatcher;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by LX on 2015/3/10.
 */
public class ZookeeperClient implements IZookeeperClient {

    Logger log = Logger.getLogger(ZookeeperClient.class);

    private CuratorFramework client;
    private String url;
    DefaultWatcher watcher;


    public ZookeeperClient(String url) {
        this.url = url;
        client = CuratorFrameworkFactory.newClient(url, new ExponentialBackoffRetry(1000, 3));
        client.start();
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                log.info("Connection状态监听");
                log.info(connectionState);
            }
        });
        watcher = new DefaultWatcher(client);
        log.info(client.getState());
    }

    /**
     * 取得子节点
     *
     * @param note
     * @return
     * @throws Exception
     */
    public List<String> getchildNote(ZooNote note) throws Exception {
        List<String> noteList = client.getChildren().forPath(note.getNotePath());
        return noteList;
    }

    /**
     * 添加节点
     *
     * @param note
     * @param ephemeral
     */
    @Override
    public void createNote(ZooNote note, boolean ephemeral) {
        try {
            String notePath;
            if (note.getParentNote() == null) {
                notePath = note.notePath;
            } else {
                notePath = note.getParentNote().getNotePath() + note.getNotePath();
            }
            if (!this.checkExists(notePath)) {
                CreateMode mode;
                if (ephemeral) {
                    mode = CreateMode.EPHEMERAL;
                } else {
                    mode = CreateMode.PERSISTENT;
                }

                client.create().withMode(mode).forPath(notePath);

            } else {
                log.info("节点：" + notePath + ",已存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    /**
     * 设置节点数据
     *
     * @param path
     * @param data
     * @throws Exception
     */
    public void setNoteData(String path, byte[] data) throws Exception {
        client.setData().forPath(path, data);
    }

    /**
     * 监听Note
     *
     * @param note
     * @throws Exception
     */
    public void watcherNote(ZooNote note) throws Exception {
        watcher.watcherNote(note);
    }

    /**
     * 监听Note的ChildNote
     *
     * @param note
     * @throws Exception
     */
    public void watcherChildNote(String note) throws Exception {
        watcher.watcherChildNote(note);
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
    public void deleteNote(ZooNote note) throws Exception {
        String notePath;
        if (note.getParentNote() == null) {
            notePath = note.getNotePath();
        } else {
            notePath = note.getParentNote().getNotePath() + note.getNotePath();
        }
        client.delete().forPath(notePath);
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
