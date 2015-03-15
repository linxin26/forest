package co.solinx.forest.config;

/**
 * Created by LX on 2015/3/9.
 */
public class ReferenceConfig extends AbstractConfig {

    public String id;
    public String name;
    public String interfaceName;
    public String ref;
    public String protocol;


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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "ReferenceConfig{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", ref='" + ref + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
