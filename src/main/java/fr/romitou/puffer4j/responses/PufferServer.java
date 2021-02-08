package fr.romitou.puffer4j.responses;

public class PufferServer {

    private String id;

    private String ip;

    private String name;

    private PufferNode node;

    private Integer nodeId;

    private Integer port;

    private String type;

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

    @Override
    public String toString() {
        return "PufferServer{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                ", node=" + node +
                ", nodeId=" + nodeId +
                ", port=" + port +
                ", type='" + type + '\'' +
                '}';
    }
}
