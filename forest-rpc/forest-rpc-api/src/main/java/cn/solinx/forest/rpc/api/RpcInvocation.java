package cn.solinx.forest.rpc.api;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by linx on 2015/4/28.
 */
public class RpcInvocation implements Invocation {


    private Method method;
    private Object[] paramters;
    private boolean async=false;

    public RpcInvocation() {
    }

    public RpcInvocation(Method method, Object[] paramters) {
        this.method = method;
        this.paramters = paramters;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getParameters() {
        return paramters;
    }

    @Override
    public boolean getAsync() {
        return async;
    }

    @Override
    public void setMethod(Method method) {
        this.method=method;
    }

    @Override
    public void setParameters(Object[] objects) {
        this.paramters=objects;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    @Override
    public String toString() {
        return "RpcInvocation{" +
                "method=" + method +
                ", paramters=" + Arrays.toString(paramters) +
                '}';
    }
}
