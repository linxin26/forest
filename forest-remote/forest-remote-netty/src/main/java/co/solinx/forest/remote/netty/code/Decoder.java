package co.solinx.forest.remote.netty.code;

import co.solinx.forest.common.serialize.JdkSerialize;
import co.solinx.forest.remote.exchange.Response;
import co.solinx.forest.remote.exchange.RpcException;
import co.solinx.forest.remote.transport.ITransporter;
import co.solinx.forest.remote.transport.NettyTransporter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by linx on 2015/4/11.
 * 解码器
 */
public class Decoder extends ByteToMessageDecoder {

    Logger logger = Logger.getLogger(Decoder.class);
    JdkSerialize serialize = new JdkSerialize();

    ITransporter transporter;

    public Decoder() {
    }

    public Decoder(ITransporter transporter) {
        this.transporter = transporter;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        if (msg.readableBytes() >= 11) {
            //魔数
            byte magic = msg.readByte();
            //类型
            byte flagType = msg.readByte();
            int sequence = msg.readInt();
            //内容长度
            int bodyLength = msg.readInt();
            //状态
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
        logger.info("----------------------------convertRequestByByte");
        logger.info(obj);
        if(obj instanceof Response){
            transporter.received((Response) obj);
        }
        return obj;
    }
}
