package co.solinx.forest.demo.impl;

import co.solinx.forest.demo.api.IHelloForestService;

import java.io.Serializable;

/**
 * Created by LX on 2015/3/9.
 */
public class HelloForestServiceImpl implements IHelloForestService, Serializable {

    @Override
    public void hello() {
        System.out.println(" HelloForestServiceImpl ");
    }
}
