package cn.solinx.forest.rpc.api;

/**
 * Created by linx on 2015/4/26.
 * reference与export服务的接口
 */
public interface Invoker {


    Object invoke(Invocation invocation) throws Exception;

}
