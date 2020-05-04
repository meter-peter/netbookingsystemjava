package netbookingsystem.server.auth;

public class User {
    String username;
    String password;
    String email;
    String firstname;
    String latsname;

    public User(String username, String password, String email, String firstname, String latsname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.latsname = latsname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLatsname() {
        return latsname;
    }

    public void setLatsname(String latsname) {
        this.latsname = latsname;
    }
}
