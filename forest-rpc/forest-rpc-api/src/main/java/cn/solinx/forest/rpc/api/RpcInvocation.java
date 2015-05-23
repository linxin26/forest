package cn.solinx.forest.rpc.api;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by linx on 2015/4/28.
 */
public class RpcInvocation implements Invocation {


    private Method method;
    private Object[] paramters;

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
    public String toString() {
        return "RpcInvocation{" +
                "method=" + method +
                ", paramters=" + Arrays.toString(paramters) +
                '}';
    }
}
