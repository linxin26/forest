package co.solinx.forest.common.serialize;

import java.io.IOException;

/**
 * Created by linx on 2015/4/21.
 */
public interface ISerialize {

    public byte[] serizlize(Object obj) throws IOException;

    public Object deSerizlize(byte[] datas) throws IOException, ClassNotFoundException;

}
