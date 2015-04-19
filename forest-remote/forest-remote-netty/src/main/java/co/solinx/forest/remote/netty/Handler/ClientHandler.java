package co.solinx.forest.remote.netty.Handler;

import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.exchange.Response;
import io.netty.buffer.UnpooledUnsafeDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * 请求引用服务
 * Created by LX on 2015/3/15.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    Logger logger = Logger.getLogger(ClientHandler.class);
    Method method;
    Object api;
    Object result;
    Object[] params;

    public ClientHandler(Object api, Method method,Object[] params) {
//        logger.info("api="+api);
//        logger.info("method="+method);
        this.method = method;
        this.api = api;
        this.params=params;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Request request=new Request();
        String data;
        if(params!=null){
            data=api.toString() + "," + method.getName()+","+params[0].toString()+","+params[0].getClass().getTypeName();
        }else{
            data=api.toString() + "," + method.getName();
        }
//        Object[] paramType=new Object[params.length];
//        for (int i = 0; i < params.length; i++) {
//            paramType[i]=params[i].getClass().getTypeName();
//        }
        request.setData(data);
        request.setParam(params);
//        request.setParamType(paramType);
        ctx.writeAndFlush(request);
        logger.info(request);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response response= (Response) msg;
        this.result=response.getResult();

    }

    public Object getRceiveMessage() {
        return result;
    }
}
