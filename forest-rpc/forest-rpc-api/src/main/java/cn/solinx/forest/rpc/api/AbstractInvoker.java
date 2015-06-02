package cn.solinx.forest.rpc.api;

import co.solinx.forest.remote.exchange.Request;
import co.solinx.forest.remote.exchange.Response;
import co.solinx.forest.remote.invoker.NettyInvoker;
import co.solinx.forest.remote.transport.ITransporter;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by linx on 2015/4/26.
 */
public abstract class AbstractInvoker implements Invoker {

    Logger logger = Logger.getLogger(AbstractInvoker.class);

    private String interfaceName;
    private String address;
    private HashMap<String, ITransporter> transporterList = new HashMap<>();

    @Override
    public Object invoke(Invocation invocation) throws Exception {
        Object result = null;
//        try {

        Response response = this.send(invocation.getMethod(), invocation.getParameters());
        logger.info(invocation);
//        logger.info("++++++++++++++++++++++++++++++++");
        if(response!=null) {
            if (!response.getResult().equals("empty")) {
                result = response.getResult();
            }
        }
//            result = nettyInvoker.clientInvoker(interfaceName, address, invocation.getMethod(), invocation.getParameters());
//        } catch (InterruptedException e) {
//
//        }
        return result;
    }

    public Response send(Method method, Object[] params) {
        String data;
        data = interfaceName.toString() + "," + method.getName();
        Object[] paramType = null;
        //处理参数，参数类型
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                paramType = new Object[params.length];
                paramType[i] = params[i].getClass().getTypeName();
            }
        }
        Random random = new Random();
        Request request = new Request();
        request.setId(random.nextInt());
        request.setData(data);
        request.setParam(params);
        request.setParamType(paramType);
        logger.info(transporterList);
        logger.info(address);
        return transporterList.get(address).send(request);
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, ITransporter> getTransporterList() {
        return transporterList;
    }
}
