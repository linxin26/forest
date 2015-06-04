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
    private static Map<Long, DefaultFuture> FUTURES = new ConcurrentHashMap<>();
    private Response response;
    Logger logger=Logger.getLogger(DefaultFuture.class);

    public DefaultFuture(Request request) {
        this.id = request.getId();
        FUTURES.put(this.id, this);
    }

    public static void revice(Response response) {
        DefaultFuture future = FUTURES.get(Long.valueOf(response.getId()));

        future.doRevice(response);
    }

    public void doRevice(Response response) {
        this.response = response;
    }

    @Override
    public Object get() {
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
