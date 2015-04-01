package co.solinx.forest.config;

/**
 * Created by LX on 2015/4/1.
 */
public class ProtocolConfig extends AbstractConfig {

    private String name;
    private int port;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
