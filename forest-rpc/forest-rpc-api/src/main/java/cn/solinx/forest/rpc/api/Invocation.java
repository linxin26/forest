package cn.solinx.forest.rpc.api;

import java.lang.reflect.Method;

/**
 * Created by linx on 2015/4/28.
 */
public interface Invocation {

    /**
     * MethodName
     *
     * @return
     */
    Method getMethod();

    /**
     * ParameterTypes
     *
     * @return
     */
    Object[] getParameters();
}
