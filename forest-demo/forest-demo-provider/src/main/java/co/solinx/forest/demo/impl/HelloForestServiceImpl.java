package co.solinx.forest.demo.impl;

import co.solinx.forest.demo.api.IHelloForestService;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * Created by LX on 2015/3/9.
 */
public class HelloForestServiceImpl implements IHelloForestService, Serializable {

    Logger logger=Logger.getLogger(HelloForestServiceImpl.class);

    @Override
    public void hello() {
        System.out.println(" HelloForestServiceImpl ");
    }

    public String print(String i){
        if (i.equals("5")){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info(Thread.currentThread().getName()+"    "+i);
        return "HelloForest"+i;
    }
}
