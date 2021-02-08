package fr.romitou.puffer4j.responses;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PufferNode that = (PufferNode) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(privateHost, that.privateHost)
                && Objects.equals(privatePort, that.privatePort)
                && Objects.equals(publicHost, that.publicHost)
                && Objects.equals(publicPort, that.publicPort)
                && Objects.equals(sftpPort, that.sftpPort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, privateHost, privatePort, publicHost, publicPort, sftpPort);
    }

    @Override
    public String toString() {
        return "PufferNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", privateHost='" + privateHost + '\'' +
                ", privatePort=" + privatePort +
                ", publicHost='" + publicHost + '\'' +
                ", publicPort=" + publicPort +
                ", sftpPort=" + sftpPort +
                '}';
    }
}
