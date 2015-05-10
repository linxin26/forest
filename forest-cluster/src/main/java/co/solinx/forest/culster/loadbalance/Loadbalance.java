package co.solinx.forest.culster.loadbalance;

import java.util.List;

/**
 * Created by linx on 2015-05-10.
 */
public interface Loadbalance {


    public String select(List<String> providerList);

}
