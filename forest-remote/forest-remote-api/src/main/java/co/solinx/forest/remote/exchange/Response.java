package co.solinx.forest.remote.exchange;

import java.io.Serializable;

/**
 * Created by linx on 2015/4/9
 *
 */
public class Response implements Serializable {

    private int id;
    private Object result;
    private String errorMsg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
