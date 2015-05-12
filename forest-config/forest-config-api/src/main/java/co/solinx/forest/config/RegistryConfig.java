package co.solinx.forest.config;

import java.util.Map;

/**
 * 注册中心配置类
 * Created by LX on 2015/3/3.
 */
public class RegistryConfig extends AbstractConfig {

    public static final String NO_AVAILABLE = "N/A";

    private String id;

    // 注册中心地址
    private String address;

    // 注册中心登录用户名
    private String username;

    // 注册中心登录密码
    private String password;

    // 注册中心缺省端口
    private Integer port;

    // 注册中心协议
    private String protocol;

    // 客户端实现
    private String transporter;

    private String server;

    private String client;

    private String cluster;

    private String group;

    private String version;

    // 注册中心请求超时时间(毫秒)
    private Integer timeout;

    // 注册中心会话超时时间(毫秒)
    private Integer session;

    // 动态注册中心列表存储文件
    private String file;

    // 停止时等候完成通知时间
    private Integer wait;

    // 启动时检查注册中心是否存在
    private Boolean check;

    // 在该注册中心上注册是动态的还是静态的服务
    private Boolean dynamic;

    // 在该注册中心上服务是否暴露
    private Boolean register;

    // 在该注册中心上服务是否引用
    private Boolean subscribe;

    // 自定义参数
    private Map<String, String> parameters;

    // 是否为缺省
    private Boolean isDefault;

    public RegistryConfig() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RegistryConfig(String address) {
        setAddress(address);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "RegistryConfig{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                ", protocol='" + protocol + '\'' +
                ", transport='" + transporter + '\'' +
                ", server='" + server + '\'' +
                ", client='" + client + '\'' +
                ", cluster='" + cluster + '\'' +
                ", group='" + group + '\'' +
                ", version='" + version + '\'' +
                ", timeout=" + timeout +
                ", session=" + session +
                ", file='" + file + '\'' +
                ", wait=" + wait +
                ", check=" + check +
                ", dynamic=" + dynamic +
                ", register=" + register +
                ", subscribe=" + subscribe +
                ", parameters=" + parameters +
                ", isDefault=" + isDefault +
                '}';
    }
}
