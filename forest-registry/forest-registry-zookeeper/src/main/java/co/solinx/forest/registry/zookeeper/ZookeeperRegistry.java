package co.solinx.forest.registry.zookeeper;

import co.solinx.forest.common.utils.InetAddressUtils;
import co.solinx.forest.registry.api.AbstractRegistry;
import co.solinx.remote.zookeeper.ZookeeperClient;
import co.solinx.remote.zookeeper.support.ZooNote;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by LX on 2015/3/10.
 */
public class ZookeeperRegistry extends AbstractRegistry {

    Logger log = Logger.getLogger(ZookeeperRegistry.class);

    public static String ROOT_NOTE = "forest";
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


    /**
     * 添加Root节点
     */
    private void createRoot() {
        ZooNote note = new ZooNote();
        note.setNoteName(ZookeeperRegistry.ROOT_NOTE);
        note.setNotePath(ZooNote.NOTE_PATH_SEPARATOR + ZookeeperRegistry.ROOT_NOTE);
        note.setParentNote(null);
        note.setNoteData(ZookeeperRegistry.ROOT_NOTE);
        client.createNote(note, false);
    }


    public void registryServerApi(String service) {
        ZooNote note = new ZooNote();
        note.setNoteName(service);
        note.setNotePath(ZooNote.NOTE_PATH_SEPARATOR + ZookeeperRegistry.ROOT_NOTE + ZooNote.NOTE_PATH_SEPARATOR + service);
        note.setParentNote(null);
        note.setNoteData(service);
        client.createNote(note, false);
    }

    /**
     * 添加 serviceApi的 Consumer节点
     *
     * @param serviceApi
     */
    private void createConsumerNote(String serviceApi) {
        ZooNote note = new ZooNote();
        note.setNoteName(ZookeeperRegistry.PROVIDRES_NOTE);
        note.setNoteData(ZookeeperRegistry.PROVIDRES_NOTE);
        note.setNotePath(ZooNote.NOTE_PATH_SEPARATOR + ZookeeperRegistry.ROOT_NOTE + "/" + serviceApi + "/" + ZookeeperRegistry.CONSUMERS_NOTE);
        note.setParentNote(null);
        client.createNote(note, false);
    }

    /**
     * 添加 serviceApi的Provider节点
     *
     * @param serviceApi
     */
    private void createProviderNote(String serviceApi) {
        ZooNote note = new ZooNote();
        note.setNoteName(ZookeeperRegistry.PROVIDRES_NOTE);
        note.setNoteData(ZookeeperRegistry.PROVIDRES_NOTE);
        note.setNotePath(ZooNote.NOTE_PATH_SEPARATOR + ZookeeperRegistry.ROOT_NOTE + "/" + serviceApi + "/" + ZookeeperRegistry.PROVIDRES_NOTE);
        note.setParentNote(null);
        client.createNote(note, false);
    }

    /**
     * 注册Consumer
     *
     * @param api
     */
    public void registryConsumer(String api) {
        this.createRoot();
        this.registryServerApi(api);
        this.createConsumerNote(api);

        String address = InetAddressUtils.findAddress();
        ZooNote serviceNote = new ZooNote();
        serviceNote.setNoteData(address);
        serviceNote.setNoteName(address);
        serviceNote.setNotePath(ZooNote.NOTE_PATH_SEPARATOR + ZookeeperRegistry.ROOT_NOTE + "/" + api + "/" + ZookeeperRegistry.CONSUMERS_NOTE + "/" + address);
        serviceNote.setParentNote(null);
        client.createNote(serviceNote, true);
    }

    /**
     * 注册 service
     *
     * @param service
     * @param api
     * @throws UnsupportedEncodingException
     */
    public void registryService(String service, String api, int port) throws UnsupportedEncodingException {
        this.createRoot();
        this.registryServerApi(api);
        this.createProviderNote(api);

        String serviceImplNote = URLEncoder.encode("forest://" + InetAddressUtils.findAddress() + ":" + port + "/" + service, "UTF-8");
        ZooNote serviceNote = new ZooNote();
        serviceNote.setNoteData(service);
        serviceNote.setNoteName(service);
        serviceNote.setNotePath(ZooNote.NOTE_PATH_SEPARATOR + ZookeeperRegistry.ROOT_NOTE + "/" + api + "/" + ZookeeperRegistry.PROVIDRES_NOTE + "/" + serviceImplNote);
        serviceNote.setParentNote(null);
        client.createNote(serviceNote, true);
    }


    public void unRegisterService(String service) throws Exception {
        ZooNote note = new ZooNote();
        note.setNoteName(service);
        note.setNotePath(ZooNote.NOTE_PATH_SEPARATOR + service);
        client.deleteNote(note);
    }
}
