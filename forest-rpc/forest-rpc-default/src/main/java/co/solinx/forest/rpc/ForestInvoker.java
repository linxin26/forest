package co.solinx.forest.rpc;


import cn.solinx.forest.rpc.api.AbstractInvoker;
import cn.solinx.forest.rpc.api.Invocation;
import cn.solinx.forest.rpc.api.RpcContext;
import co.solinx.forest.common.ResponseFuture;
import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.transport.ITransporter;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by linx on 2015/4/26.
 */
public class ForestInvoker extends AbstractInvoker {

    Logger logger=Logger.getLogger(ForestInvoker.class);
    private HashMap<String, ITransporter> transporterList = new HashMap<>();

    public ForestInvoker(String interfaceName,String addres) {

        this.setInterfaceName(interfaceName);
        this.setAddress(addres);
    }

    @Override
    public Object invoke(Invocation invocation) throws Exception {

        boolean async=invocation.getAsync();
        logger.info("-------------------------异步：" + async);
        if(async){
            ResponseFuture result= (ResponseFuture) super.invoke(invocation);
            RpcContext.getContext().setFuture(new FutureAdapter<>(result));
            return new String();
        }else{
            RpcContext.getContext().setFuture(null);
            return ((ResponseFuture)super.invoke(invocation)).get();
        }


    }




}
