package co.solinx.remote.zookeeper.watcher;

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
    private String watcherNote;

    public DefaultWatcher(CuratorFramework curatorClient, String watcherNote) {
        this.curatorClient = curatorClient;
        this.watcherNote = watcherNote;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        logger.info("监听事件 {}", watchedEvent);
    }

    public void start() throws Exception {
        curatorClient.checkExists().usingWatcher(this).forPath(watcherNote);
    }


}
