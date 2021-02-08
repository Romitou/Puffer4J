package fr.romitou.puffer4j.responses;

import java.util.List;

public class PufferPagedServers {

    private PufferPaging paging;

    private List<PufferServer> servers;

    public PufferPaging getPaging() {
        return paging;
    }

    public void setPaging(PufferPaging paging) {
        this.paging = paging;
    }

    public List<PufferServer> getServers() {
        return servers;
    }

    public void setServers(List<PufferServer> servers) {
        this.servers = servers;
    }
}
