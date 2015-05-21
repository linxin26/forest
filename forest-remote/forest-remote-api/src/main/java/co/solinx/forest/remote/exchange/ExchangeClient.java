package co.solinx.forest.remote.exchange;

import co.solinx.forest.remote.Client;

/**
 * Created by linx on 2015-05-21.
 */
public class ExchangeClient implements Client{


    private Client client;


    @Override
    public void connect() {
        client.connect();
    }
}
