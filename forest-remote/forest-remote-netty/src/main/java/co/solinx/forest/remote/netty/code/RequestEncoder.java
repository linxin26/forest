package co.solinx.forest.remote.netty.code;

import co.solinx.forest.remote.exchange.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by linx on 2015/4/11.
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
    }
}
