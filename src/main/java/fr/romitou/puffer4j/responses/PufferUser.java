package fr.romitou.puffer4j.responses;

public class PufferUser {

    private String email;

    private Integer id;

    private String newPassword;

    private String password;

    private String username;

    public PufferUser(String email, Integer id, String newPassword, String password, String username) {
        this.email = email;
        this.id = id;
        this.newPassword = newPassword;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
