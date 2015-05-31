package co.solinx.forest.common.byteCode;

/**
 * Created by linx on 2015-05-31.
 */
public class LoadClass {

    public static String getClassName(String classz) throws Exception {
        String className = null;
        try {
             className=Class.forName(classz).getName();
        } catch (ClassNotFoundException e) {
            throw new Exception("class.forName 失败",e);
        }
        return className;
    }

}
