package co.solinx.forest.registry.zookeeper;

import co.solinx.forest.registry.IRegistry;
import co.solinx.remote.zookeeper.ZookeeperClient;
import co.solinx.remote.zookeeper.support.ZooNote;
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

    public void registerService(String service) {
//        client.create("/forest/co.solinx.forest", true);
        try {
            ZooNote note = new ZooNote();
            note.setNoteName(service);
            note.setNotePath(ZooNote.NOTE_PATH_SEPARATOR+service);
            note.setParentNote(null);
            note.setNoteData(service);

            client.createNote(note, true);
            client.watcherNote(note);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unRegisterService(String service) throws Exception {
        ZooNote note = new ZooNote();
        note.setNoteName(service);
        note.setNotePath(ZooNote.NOTE_PATH_SEPARATOR+service);
        client.deleteNote(note);
    }
}
