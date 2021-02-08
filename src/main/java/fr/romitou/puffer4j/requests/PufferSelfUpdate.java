package fr.romitou.puffer4j.requests;

public class PufferSelfUpdate {

    private final String email;
    private final String password;
    private final String username;

    /**
     * Create a self update object in order to update your current user.
     *
     * @param email The new email address.
     * @param password The new password.
     * @param username The new username.
     */
    public PufferSelfUpdate(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

}
