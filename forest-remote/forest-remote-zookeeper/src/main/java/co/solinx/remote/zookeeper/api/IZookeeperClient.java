package co.solinx.remote.zookeeper.api;

import co.solinx.remote.zookeeper.support.ZooNote;

import java.util.List;

/**
 * Created by LX on 2015/3/10.
 * ZookeeperClient Api
 */
public interface IZookeeperClient {

    public void createNote(ZooNote note, boolean ephemeral);

    public void deleteNote(ZooNote note) throws Exception;

    public List<String> getChildren(String note);

    public List<String> addChildrenListener(String note, IChildrenListener listener);

    public void remoteChildrenListener(String note, IChildrenListener listener);

    public boolean isConnected();

    public void close();


}
