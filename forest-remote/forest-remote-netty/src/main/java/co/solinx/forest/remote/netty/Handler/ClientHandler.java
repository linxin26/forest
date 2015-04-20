package co.solinx.forest.remote.netty.Handler;

import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.exchange.Response;
import co.solinx.forest.remote.netty.client.NettyClient;
import io.netty.buffer.UnpooledUnsafeDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.locks.Condition;

/**
 * 请求引用服务
 * Created by LX on 2015/3/15.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    Logger logger = Logger.getLogger(ClientHandler.class);
    Method method;
    Object api;
    Response resultResponse;
    Object[] params;
    Request request = new Request();

    public ClientHandler(Object api, Method method, Object[] params) {
//        logger.info("api="+api);
//        logger.info("method="+method);
        this.method = method;
        this.api = api;
        this.params = params;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String data;
        data = api.toString() + "," + method.getName();
        Object[] paramType = null;
        //处理参数，参数类型
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                paramType = new Object[params.length];
                paramType[i] = params[i].getClass().getTypeName();
            }
        }
        Random random=new Random();
        request.setId(random.nextInt());
        request.setData(data);
        request.setParam(params);
        request.setParamType(paramType);
        ctx.writeAndFlush(request);
        logger.info(request);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response response = (Response) msg;
        this.resultResponse = response;
        if(request.getId()!=resultResponse.getId()){
            throw new Exception("invoker error");
        }
        logger.info(response);
        logger.info(request);
    }

    public Response getRceiveMessage() {
        return resultResponse;
    }
}
