package cn.solinx.forest.rpc.api;

import co.solinx.forest.remote.exchange.Request;
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
        co.solinx.forest.common.ResponseFuture response = this.send(invocation.getMethod(), invocation.getParameters());
        logger.info(invocation);
        return response;
    }

    public co.solinx.forest.common.ResponseFuture send(Method method, Object[] params) {
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

    public void setTransporterList(HashMap<String, ITransporter> transporterList) {
        this.transporterList = transporterList;
    }
}
