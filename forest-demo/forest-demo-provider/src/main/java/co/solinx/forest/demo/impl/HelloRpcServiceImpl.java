package co.solinx.forest.demo.impl;

import co.solinx.forest.demo.api.IHelloRpcService;

import java.io.Serializable;

/**
 * Created by LX on 2015/3/9.
 */
public class HelloRpcServiceImpl implements IHelloRpcService, Serializable {

    @Override
    public void hello() {
        System.out.println(" HelloRpcServiceImpl ");
    }

    public String print() {
        return "print rpc";
    }
}
