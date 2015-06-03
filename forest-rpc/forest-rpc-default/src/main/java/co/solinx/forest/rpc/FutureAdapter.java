package co.solinx.forest.rpc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by linx on 2015-06-03.
 */
public class FutureAdapter<v> implements Future<v>{

    private String future;

    public FutureAdapter(String future){
        this.future=future;
    }


    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        boolean result=false;
        if(future!=null){
            result=true;
        }
        return result;
    }

    @Override
    public v get() throws InterruptedException, ExecutionException {
        return (v) future;
    }

    @Override
    public v get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return (v) future;
    }
}
