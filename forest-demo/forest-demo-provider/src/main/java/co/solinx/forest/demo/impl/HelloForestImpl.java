package co.solinx.forest.demo.impl;

import co.solinx.forest.demo.api.IHelloForest;

/**
 * Created by LX on 2015/3/9.
 */
public class HelloForestImpl implements IHelloForest {

    @Override
    public void hello() {
        System.out.println(" HelloForestImpl ");
    }
}
