package co.solinx.forest.config;

/**
 * Created by LX on 2015/3/9.
 */
public class ServiceConfig extends AbstractConfig {

    public String id;
    public String name;
    public String interfaceApi;
    public String ref;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getInterfaceApi() {
        return interfaceApi;
    }

    public void setInterfaceApi(String interfaceApi) {
        this.interfaceApi = interfaceApi;
    }
}
