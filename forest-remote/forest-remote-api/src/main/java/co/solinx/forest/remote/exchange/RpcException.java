package co.solinx.forest.remote.exchange;

/**
 * Created by linx on 2015/4/22.
 */
public class RpcException extends RuntimeException {

    private static final long serialVersionUID = 7815434214258364734L;

    private int code;

    public RpcException() {
        super();
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(String message) {
        super(message);
    }

    public RpcException(String message, int code) {
        super(message);
        this.code = code;
    }

    public RpcException(Throwable cause) {
        super(cause);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
