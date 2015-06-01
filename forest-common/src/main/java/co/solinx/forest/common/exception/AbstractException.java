package co.solinx.forest.common.exception;

/**
 * Created by linx on 2015-05-30.
 */
public abstract class AbstractException extends RuntimeException{

    private int code;

    public AbstractException() {

    }

    public AbstractException(int code,String message){
        super(message);
        this.code=code;
    }

    public AbstractException(String message) {
        super(message);
    }



    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractException(int code,String message, Throwable cause) {
        super(message, cause);
        this.code=code;
    }

}
