package fr.romitou.puffer4j.responses;

public class PufferNode {

    private Integer id;

    private String name;

    private String privateHost;

    private Integer privatePort;

    private String publicHost;

    private Integer publicPort;

    private Integer sftpPort;

    public PufferNode(Integer id, String name, String privateHost, Integer privatePort, String publicHost, Integer publicPort, Integer sftpPort) {
        this.id = id;
        this.name = name;
        this.privateHost = privateHost;
        this.privatePort = privatePort;
        this.publicHost = publicHost;
        this.publicPort = publicPort;
        this.sftpPort = sftpPort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivateHost() {
        return privateHost;
    }

    public void setPrivateHost(String privateHost) {
        this.privateHost = privateHost;
    }

    public Integer getPrivatePort() {
        return privatePort;
    }

    public void setPrivatePort(Integer privatePort) {
        this.privatePort = privatePort;
    }

    public String getPublicHost() {
        return publicHost;
    }

    public void setPublicHost(String publicHost) {
        this.publicHost = publicHost;
    }

    public Integer getPublicPort() {
        return publicPort;
    }

    public void setPublicPort(Integer publicPort) {
        this.publicPort = publicPort;
    }

    public Integer getSftpPort() {
        return sftpPort;
    }

    public void setSftpPort(Integer sftpPort) {
        this.sftpPort = sftpPort;
    }

}