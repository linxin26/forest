package co.solinx.forest.remote.netty.test.code;

import co.solinx.forest.remote.exchange.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by 鑫 on 2015/4/10.
 */
public class RequestEncoder extends MessageToMessageEncoder<Request> {

    Logger logger=Logger.getLogger(RequestEncoder.class);


    public byte[] convertByteByRequest(Object obj){
        Request request= (Request) obj;
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        try {
            ObjectOutputStream output=new ObjectOutputStream(outputStream);
            output.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Request msg, List<Object> out) throws Exception {

        logger.info(msg);
        byte[] data= this.convertByteByRequest(msg);
        ByteBuf encoded = ctx.alloc().buffer(data.length);

        encoded.writeBytes(data);
        out.add(encoded);
        logger.info(data.length);
        logger.info(out.get(0));
    }

//    @Override
//    protected void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
////        ByteBuf byteBuf=ctx.alloc().buffer();
////        byteBuf.writeBytes(this.convertByteByRequest(msg));
////        out.add(byteBuf);
//        //把int转换为字节数组
//        byte[] result = new byte[4];
//        result[0] = (byte) (msg.intValue() >>> 24);//取最高8位放到0下标
//        result[1] = (byte) (msg.intValue() >>> 16);//取次高8为放到1下标
//        result[2] = (byte) (msg.intValue() >>> 8); //取次低8位放到2下标
//        result[3] = (byte) (msg.intValue());      //取最低8位放到3下标
//
//        ByteBuf encoded = ctx.alloc().buffer(Integer.BYTES);
//        encoded.writeBytes(result);
//        out.add(encoded);
//    }
}
