package fr.romitou.puffer4j.responses;

import java.util.List;

public class PufferSession {

    private String session;

    private List<String> scopes;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

}
