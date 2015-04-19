
package co.solinx.forest.remote.exchange;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by linx on 2015/4/8.
 * 动态代理请求
 */
public class Request implements Serializable{

    private int id;
    private Object data;
    private long RequestTime;
    private Object[] param;
    private Object[] paramType;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(long requestTime) {
        RequestTime = requestTime;
    }

    public Object[] getParam() {
        return param;
    }

    public void setParam(Object[] param) {
        this.param = param;
    }

    public Object[] getParamType() {
        return paramType;
    }

    public void setParamType(Object[] paramType) {
        this.paramType = paramType;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", data=" + data +
                ", RequestTime=" + RequestTime +
                ", param=" + Arrays.toString(param) +
                '}';
    }


}
