package co.solinx.forest.remote.netty.code;

import co.solinx.forest.remote.exchange.Request;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by linx on 2015/4/11.
 */
public class Decoder extends ByteToMessageDecoder {

    Logger logger = Logger.getLogger(Decoder.class);


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        if (msg.readableBytes() >= 11) {
            byte magic = msg.readByte();
            byte flagType = msg.readByte();
            int sequence = msg.readInt();
            int bodyLength = msg.readInt();
            byte status=msg.readByte();
            if (Encoder.MAGIC == magic && Encoder.FLAG_REQUEST == flagType) {
                if (msg.readableBytes() >= bodyLength) {
                    byte[] data = new byte[bodyLength];
                    msg.readBytes(data);
                    Object request = this.convertRequestByByte(data);
                    if (request != null) {
                        out.add(request);
                    }
                }
            }
        }
    }

    public Object convertRequestByByte(byte[] bytes) {
        Request request = null;
        Object obj = null;
        ByteInputStream inputStream = new ByteInputStream();
        inputStream.setBuf(bytes);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            obj = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("----------------------------convert");
        logger.info(request);
        return obj;
    }
}
