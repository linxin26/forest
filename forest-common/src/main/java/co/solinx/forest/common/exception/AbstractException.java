package co.solinx.forest.common.exception;

/**
 * Created by linx on 2015-05-30.
 */
public abstract class AbstractException extends RuntimeException{


    public AbstractException() {

    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }


}
