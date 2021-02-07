package fr.romitou.puffer4j.responses;

public class PufferServer {

    private Object data;

    private String id;

    private String ip;

    private String name;

    private PufferNode node;

    private Integer nodeId;

    private Integer port;

    private String type;

    private PufferScopedUser[] users;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PufferNode getNode() {
        return node;
    }

    public void setNode(PufferNode node) {
        this.node = node;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PufferScopedUser[] getUsers() {
        return users;
    }

    public void setUsers(PufferScopedUser[] users) {
        this.users = users;
    }
}
