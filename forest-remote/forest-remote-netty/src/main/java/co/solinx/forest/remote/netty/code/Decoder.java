package co.solinx.forest.remote.netty.code;

import co.solinx.forest.common.serialize.JdkSerialize;
import co.solinx.forest.remote.exchange.RpcException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by linx on 2015/4/11.
 */
public class Decoder extends ByteToMessageDecoder {

    Logger logger = Logger.getLogger(Decoder.class);
    JdkSerialize serialize = new JdkSerialize();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        if (msg.readableBytes() >= 11) {
            byte magic = msg.readByte();
            byte flagType = msg.readByte();
            int sequence = msg.readInt();
            int bodyLength = msg.readInt();
            byte status = msg.readByte();
            //检查消息魔数
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
        Object obj = null;
        try {
            //反序列化
            obj = serialize.deSerialize(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RpcException("Request deserialize exception");
        }
        logger.info("----------------------------convert");
        logger.info(obj);
        return obj;
    }
}
