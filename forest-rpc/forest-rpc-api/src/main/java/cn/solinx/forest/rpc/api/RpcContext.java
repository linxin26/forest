package cn.solinx.forest.rpc.api;

import java.util.concurrent.Future;

/**
 * Created by linx on 2015-06-03.
 */
public class RpcContext {

    private Future future;

    static RpcContext context=new RpcContext();


    public static RpcContext getContext(){
        return context;
    }


    public Future getFuture() {
        return future;
    }

    public void setFuture(Future future) {
        this.future = future;
    }
}
