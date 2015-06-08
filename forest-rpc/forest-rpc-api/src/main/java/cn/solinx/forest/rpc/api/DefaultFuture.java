package cn.solinx.forest.rpc.api;

import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.exchange.Response;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by linx on 2015-06-04.
 */
public class DefaultFuture implements co.solinx.forest.common.ResponseFuture {

    private long id;
    //存储已发送请求  <RequestID,将要返回的值>
    private final static Map<Long, DefaultFuture> FUTURES = new ConcurrentHashMap<>();
    private Response response;
    Logger logger=Logger.getLogger(DefaultFuture.class);

    public DefaultFuture(Request request) {
        this.id = request.getId();
        FUTURES.put(this.id, this);
    }


    public static void revice(Response response) {
        DefaultFuture future = FUTURES.get(Long.valueOf(response.getId()));
        System.out.println("+++++++++++++++++++++++");
        System.out.println(future);
        future.doRevice(response);
    }

    public void doRevice(Response response) {
        this.response = response;
        logger.info("----------------------doRevice");
        logger.info(response);
        FUTURES.put(Long.valueOf(response.getId()),this);
    }

    @Override
    public Object get() {
        //检查是否已经接收到数据
        while (!isDone()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isDone()) {
                break;
            }
        }
        return response.getResult();
    }

    @Override
    public boolean isDone() {
        boolean result = false;
        if (response != null) {
            result = true;
        }
        return result;
    }
}
