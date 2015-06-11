package co.solinx.forest.remote.netty.Handler;

import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.exchange.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 处理所引用的服务
 * Created by LX on 2015/3/15.
 */
public class ServiceHandler extends ChannelInboundHandlerAdapter {
    Logger logger = Logger.getLogger(ServiceHandler.class);

    List<Object> services;

    public ServiceHandler(List<Object> services) {
        this.services = services;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;
        Response response = this.readData(request);
        ctx.writeAndFlush(response);
    }

    public Response readData(Request request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Response response = new Response();
        String message = new String(request.getData().toString());
        logger.info("message:" + message);
        String[] invoke = message.split(",");
        Class<?>[] parameterTypes = null;      //参数类型
        Object[] param = request.getParam();   //参数
        Object[] paramTypeStr = request.getParamType();  //参数类型字符串
        if (paramTypeStr != null) {
            parameterTypes = new Class<?>[paramTypeStr.length];
            for (int i = 0; i < paramTypeStr.length; i++) {
                parameterTypes[i] = Class.forName((String) paramTypeStr[i]);
            }
        }
        for (int i = 0; i < services.size(); i++) {
            if (this.isInterfaceImpl(services.get(i), invoke[0])) {
                //get方法
                Method method = services.get(i).getClass().getMethod(invoke[1], parameterTypes);
                //执行方法
                Object result = method.invoke(services.get(i), param);
                logger.info("result:" + result);
                if (result == null) {
                    result = "empty";
                }
                response.setId(request.getId());
                response.setResult(result);
            }
        }
        return response;
    }


    /**
     * 检查czl是否实现intl接口
     *
     * @param czl
     * @param intl
     * @return
     */
    private boolean isInterfaceImpl(Object czl, String intl) {
        Class[] interfaces = czl.getClass().getInterfaces();
        boolean ret = false;
        for (int i = 0; i < interfaces.length; i++) {
            if (interfaces[i].getName().equals(intl)) {
                ret = true;
            }
        }
        return ret;
    }


}
