package co.solinx.forest.common.byteCode;

import co.solinx.forest.common.exception.ClassNotExistException;

/**
 * Created by linx on 2015-05-31.
 */
public class LoadClass {

    public static String getClassName(String classz) throws Exception {
        String className = null;
        try {
             className=Class.forName(classz).getName();
        } catch (ClassNotFoundException e) {
            throw new ClassNotExistException("class.forName 失败",e);
        }
        return className;
    }

}
