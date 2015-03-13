package co.solinx.remote.zookeeper.watcher;

import co.solinx.remote.zookeeper.support.ZooNote;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by LX on 2015/3/12.
 * 监听
 */
public class DefaultWatcher implements Watcher {

    Logger logger = LoggerFactory.getLogger(DefaultWatcher.class);

    private CuratorFramework curatorClient;

    public DefaultWatcher(CuratorFramework curatorClient) {
        this.curatorClient = curatorClient;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        logger.info("监听事件 {}", watchedEvent);
    }


    /**
     * watcher note
     *
     * @throws Exception
     */
    public void watcherNote(ZooNote note) throws Exception {
        curatorClient.checkExists().usingWatcher(this).forPath(note.getNotePath());
    }

    /**
     * watcher ChildNote
     *
     * @throws Exception
     */
    public void watcherChildNote(String note) throws Exception {
        curatorClient.getChildren().usingWatcher(this).forPath(note);
    }


}
