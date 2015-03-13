package co.solinx.remote.zookeeper.support;

/**
 * Created by LX on 2015/3/13.
 * ZooNote is Zookeeper Note
 */
public class ZooNote {

    public static String NOTE_PATH_SEPARATOR = "/";

    /**
     * 节点名称
     */
    public String noteName;
    /**
     * 节点数据
     */
    public String noteData;
    /**
     * 父节点
     */
    public ZooNote parentNote;
    /**
     * 节点路径
     */
    public String notePath;


    /**
     * 取得节点名称
     *
     * @return
     */
    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    /**
     * 取节点数据
     *
     * @return
     */
    public String getNoteData() {
        return noteData;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }

    /**
     * 取父节点
     *
     * @return
     */
    public ZooNote getParentNote() {
        return parentNote;
    }

    public void setParentNote(ZooNote parentNote) {
        this.parentNote = parentNote;
    }

    /**
     * 取节点路径
     *
     * @return
     */
    public String getNotePath() {
        return notePath;
    }

    public void setNotePath(String notePath) {
        this.notePath = notePath;
    }
}
