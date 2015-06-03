package cn.solinx.forest.rpc.api;

/**
 * Created by linx on 2015-06-03.
 */
public interface ResponseFuture {

    Object get();

    boolean isDone();

}
