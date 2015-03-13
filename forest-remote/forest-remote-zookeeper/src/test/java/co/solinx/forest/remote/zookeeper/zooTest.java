package co.solinx.forest.remote.zookeeper;

import co.solinx.remote.zookeeper.ZookeeperClient;
import co.solinx.remote.zookeeper.support.ZooNote;

/**
 * Created by LX on 2015/3/12.
 */
public class zooTest {
    public static void main(String[] args) {
        try {
            ZooNote note = new ZooNote();
            note.setNoteName("test");
            note.setNotePath("/test");
            note.setNoteData("testData");
            note.setParentNote(null);
            ZooNote childNote = new ZooNote();
            childNote.setNoteName("childTest");
            childNote.setNotePath("/childTest");
            childNote.setNoteData("childData");
            childNote.setParentNote(note);

            ZookeeperClient client = new ZookeeperClient("192.168.254.144");

            client.createNote(note, true);
            client.watcherNote(note);
            client.watcherChildNote(note.getNotePath());
            client.createNote(childNote, true);
//            client.deleteNote(childNote);
//            client.deleteNote(note);

            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
