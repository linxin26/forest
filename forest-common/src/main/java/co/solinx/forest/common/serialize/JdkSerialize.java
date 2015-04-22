package co.solinx.forest.common.serialize;

import java.io.*;

/**
 * jdk自带序列化
 * Created by linx on 2015/4/21.
 */
public class JdkSerialize implements ISerialize {

    /**
     * 序列化
     *
     * @param obj
     * @return
     * @throws IOException
     */
    @Override
    public byte[] serialize(Object obj) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
        objectOutput.writeObject(obj);
        return outputStream.toByteArray();
    }

    /**
     * 反序列化
     *
     * @param datas
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Object deSerialize(byte[] datas) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(datas);
        ObjectInputStream objectInput = new ObjectInputStream(inputStream);
        return objectInput.readObject();
    }
}
