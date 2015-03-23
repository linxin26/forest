package co.solinx.forest.registry.zookeeper;

import co.solinx.forest.registry.IRegistry;
import co.solinx.remote.zookeeper.ZookeeperClient;
import co.solinx.remote.zookeeper.support.ZooNote;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by LX on 2015/3/10.
 */
public class ZookeeperRegistry implements IRegistry {

    Logger log = Logger.getLogger(ZookeeperRegistry.class);

    public static String ROOT_NOTE = "forest/";
    public static String PROVIDRES_NOTE = "providers";
    public static String CONSUMERS_NOTE = "consumers";

    ZookeeperClient client;
    private static ZookeeperRegistry registry;

    public ZookeeperRegistry() {
    }

    public static ZookeeperRegistry getZookeeper() {
        if (registry == null) {
            registry = new ZookeeperRegistry();
        }
        return registry;
    }

    @Override
    public void toRegistry(String url) {
        if (client == null) {
            client = new ZookeeperClient(url.replace("zookeeper://", ""));
        }
    }

    /**
     * 获取服务实现
     *
     * @param interfaceName
     * @return
     * @throws Exception
     */
    public List<String> getServiceImplList(String interfaceName) throws Exception {
        ZooNote api = new ZooNote();
        api.setNoteName(interfaceName);
        api.setNotePath("/forest/" + interfaceName + "/providers");
        return client.getchildNote(api);
    }


    public void registerService(String service) {
//        client.create("/forest/co.solinx.forest", true);
        try {
            ZooNote note = new ZooNote();
            note.setNoteName(service);
            note.setNotePath(ZooNote.NOTE_PATH_SEPARATOR + service);
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
        note.setNotePath(ZooNote.NOTE_PATH_SEPARATOR + service);
        client.deleteNote(note);
    }
}
