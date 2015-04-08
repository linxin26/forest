package co.solinx.forest.remote.exchange;

/**
 * Created by linx on 2015/4/8.
 * 引用的请求
 */
public class Request {

    private int id;
    private Object data;
    private long RequestTime;


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

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", data=" + data +
                ", RequestTime=" + RequestTime +
                '}';
    }
}
