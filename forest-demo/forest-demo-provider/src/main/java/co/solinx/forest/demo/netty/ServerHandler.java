package co.solinx.forest.demo.netty;

import org.jboss.netty.channel.*;

import java.lang.reflect.Method;

/**
 * Created by LX on 2015/3/21.
 */
public class ServerHandler extends SimpleChannelHandler {

    Object[] services;

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("Export service " + services.getClass().getName() + " on port 19000");

        String message = (String) e.getMessage();
        System.out.println("message:" + message);
        String[] invoke = message.split(",");
        Class<?>[] parameterTypes = null;
        Object[] arguments = null;
        try {
            for (int i = 0; i < services.length; i++) {
                if (this.isInterfaceImpl(services[i], invoke[0])) {
                    Method method = services[i].getClass().getMethod(invoke[1], parameterTypes);
                    Object result = method.invoke(services[i], arguments);
                    System.out.println("result:" + result);
                    if(result!=null) {
                        ctx.getChannel().write(result);
                    }
                } else {

                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
//        super.messageReceived(ctx, e);
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

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
        System.out.println("server Connect");

    }

    public ServerHandler(Object[] services) {
//        if (service == null)
//            throw new IllegalArgumentException("service instance == null");
        this.services = services;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println(e.getCause());
        super.exceptionCaught(ctx, e);
    }
}
