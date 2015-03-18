package co.solinx.forest.remote.netty.test;

import co.solinx.forest.remote.netty.client.NettyClient;

/**
 * Created by LX on 2015/3/15.
 */
public class TestClient {
    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        client.start("nettyClient");
    }
}
