package co.solinx.forest.remote.proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 通过netty引用远程服务
 * Created by LX on 2015/3/21.
 */
public class DefaultInvocationHandler  implements InvocationHandler, Serializable {

//    Logger logger = Logger.getLogger(DefaultInvocationHandler.class);
//
//    Object result = new Object();
//    Object obj;
//    final Object lock = new Object();
//    String address;
//    Method method;
//
//
    public Object invoke(Object proxy, final Method method, Object[] args) throws Throwable {
//
//        this.method = method;
//        //启动ntty客户端
//
//        ClientBootstrap client = new ClientBootstrap();
//        final ClientHandler clientHandler = new ClientHandler(obj, method);
//        client.setFactory(new NioClientSocketChannelFactory());
//        client.setPipelineFactory(new ChannelPipelineFactory() {
//            public ChannelPipeline getPipeline() throws Exception {
//                ChannelPipeline pipeline = new DefaultChannelPipeline();
//                pipeline.addLast("decode", new ObjectDecoder());
//                pipeline.addLast("encode", new ObjectEncoder());
//                pipeline.addLast("handler", DefaultInvocationHandler.this);
//                return pipeline;
//            }
//        });
//
//        String ip = address.split(":")[0];
//        int port = Integer.parseInt(address.split(":")[1]);
//        client.connect(new InetSocketAddress(ip, port));
//
//
//        Object ret = clientHandler.getRceiveMessage();
//        synchronized (lock) {
//            lock.wait();
//        }
////        Thread.sleep(2000);
//        return result;
        return null;
    }
//
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        result = e.getMessage();
//        synchronized (lock) {
//            lock.notifyAll();
//        }
////        logger.info(result);
//    }
//
//    @Override
//    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
////        logger.info("connected");
//        ctx.getChannel().write(obj.toString() + "," + method.getName());
//    }
//
//    public DefaultInvocationHandler(Object obj, String address) {
//        this.obj = obj;
//        this.address = address;
//    }

}
