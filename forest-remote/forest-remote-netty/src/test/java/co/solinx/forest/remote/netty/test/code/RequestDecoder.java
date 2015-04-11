package co.solinx.forest.remote.netty.test.code;

import co.solinx.forest.remote.exchange.Request;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by 鑫 on 2015/4/11.
 */
public class RequestDecoder extends ByteToMessageDecoder {

    Logger logger = Logger.getLogger(RequestDecoder.class);


//    @Override
//    protected void decode(ChannelHandlerContext ctx, Request msg, List<Object> out) throws Exception {
//        logger.info(out.size());
//        logger.info(msg);
////        logger.info(byteBuf.readableBytes());
//    }

//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//
//        logger.info(out.size());
//        logger.info(in);
//        logger.info(in.readableBytes());
//        byte[] message=new byte[0];
//        in.readBytes(message);
//
//    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        byte[] data = new byte[msg.readableBytes()];
        msg.readBytes(data);
        Request request= this.convertRequestByByte(data);
        out.add(request);
        logger.info(msg);
//        out.add(msg);
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
