package fr.romitou.puffer4j.requests;

public class PufferAuth {

    private final String email;
    private final String password;

    /**
     * Create a authorization object for PufferPanel in order to retrieve user session.
     *
     * @param email    The user's email address
     * @param password The user's password
     */
    public PufferAuth(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
