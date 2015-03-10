package co.solinx.remote.zookeeper.api;

import java.util.List;

/**
 * Created by LX on 2015/3/10.
 * ZookeeperClient Api
 */
public interface IZookeeperClient {

    public void create(String path, boolean ephemeral);

    public void delete(String path);

    public List<String> getChildren(String path);

    public List<String> addChildrenListener(String path, IChildrenListener listener);

    public void remoteChildrenListener(String path, IChildrenListener listener);

    public boolean isConnected();

    public void close();
 

}
