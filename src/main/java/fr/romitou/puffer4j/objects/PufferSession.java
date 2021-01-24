package fr.romitou.puffer4j.objects;

import java.util.List;

public class PufferSession {

    private String sessionToken;

    private List<String> scopes;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

}
