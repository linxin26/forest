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
public class RequestDecoder extends ByteToMessageDecoder {

    Logger logger = Logger.getLogger(RequestDecoder.class);


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        byte[] data = new byte[msg.readableBytes()];
        msg.readBytes(data);
        Request request= this.convertRequestByByte(data);
        out.add(request);
        logger.info(msg);
    }

    public Request convertRequestByByte(byte[] bytes) {
        Request request=null;
        ByteInputStream inputStream = new ByteInputStream();
        inputStream.setBuf(bytes);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            request = (Request) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("----------------------------convert");
        logger.info(request);
        return request;
    }
}
