package co.solinx.forest.remote.netty.test;

import co.solinx.forest.remote.netty.server.NettyServer;

/**
 * Created by LX on 2015/3/15.
 */
public class TestServer {

    public static void main(String[] args) {
        NettyServer server =NettyServer.getInstance();
        server.open();
    }

}
