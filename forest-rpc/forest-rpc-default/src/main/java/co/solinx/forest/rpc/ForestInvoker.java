package co.solinx.forest.rpc;


import cn.solinx.forest.rpc.api.AbstractInvoker;
import cn.solinx.forest.rpc.api.Invocation;
import cn.solinx.forest.rpc.api.RpcContext;
import co.solinx.forest.common.byteCode.LoadClass;
import co.solinx.forest.common.extension.ExtensionLoader;
import co.solinx.forest.registry.api.AbstractRegistry;
import io.netty.util.concurrent.Future;
import org.apache.log4j.Logger;

/**
 * Created by linx on 2015/4/26.
 */
public class ForestInvoker extends AbstractInvoker {

    Logger logger=Logger.getLogger(ForestInvoker.class);

    public ForestInvoker(String interfaceName,String addres) {

        this.setInterfaceName(interfaceName);
        this.setAddress(addres);
    }

    @Override
    public Object invoke(Invocation invocation) throws Exception {

        boolean async=invocation.getAsync();
        logger.info("-------------------------异步："+async);
        if(async){
            String result= (String) super.invoke(invocation);
            RpcContext.getContext().setFuture(new FutureAdapter<String>(result));
            return new String();
        }else{
            RpcContext.getContext().setFuture(null);
            return super.invoke(invocation);
        }


    }


}
