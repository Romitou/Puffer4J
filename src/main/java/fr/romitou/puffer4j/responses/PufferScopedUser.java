package fr.romitou.puffer4j.responses;

public class PufferScopedUser {

    private String[] scopes;

    private String username;

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
