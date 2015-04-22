package co.solinx.forest.remote.netty.code;

import co.solinx.forest.common.serialize.JdkSerialize;
import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.exchange.Response;
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
public class Encoder extends MessageToMessageEncoder<Object> {


    /**
     * 协议头长度
     */
    private static final int HEADER_LENGTH = 11;
    /**
     * 魔数
     */
    public static final byte MAGIC = 0x5F;
    public static final byte FLAG_REQUEST = 0x20;
    public static final byte FLAG_RESPONSE = 0x40;
    public static final byte FLAG_EVENT = 0x60;
    //    private static final int SEQUENCE;
    private JdkSerialize serialize = new JdkSerialize();


    Logger logger = Logger.getLogger(Encoder.class);


    public byte[] convertByteByRequest(Object obj) {
        Request request = (Request) obj;
        byte[] bytes = null;
        try {
            bytes = serialize.serialize(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public byte[] convertByteByResponse(Object obj) {
        logger.info("-----------------------------Response");
        Response response = (Response) obj;
        byte[] bytes = null;
        try {
            bytes = serialize.serialize(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {

        byte[] data = null;
        if (msg instanceof Request) {
            data = this.convertByteByRequest(msg);
        } else if (msg instanceof Response) {
            data = this.convertByteByResponse(msg);
        }
        ByteBuf encoded = ctx.alloc().buffer(HEADER_LENGTH + data.length);
        encoded.writeByte(MAGIC);
        encoded.writeByte(FLAG_REQUEST);
        encoded.writeInt(1);      //Sequence
        encoded.writeInt(data.length);
        encoded.writeByte(1);     //状态
        encoded.writeBytes(data);   //body
        out.add(encoded);
    }
}
