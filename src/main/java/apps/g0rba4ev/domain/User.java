package apps.g0rba4ev.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserAuthToken> userAuthTokens = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Answer> answersSet = new HashSet<>();

    public User() {

    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        if (role.equals("admin")) {
            this.role = "admin";
        } else {
            this.role = "user";
        }

    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<UserAuthToken> getUserAuthTokens() {
        return userAuthTokens;
    }

    public Set<Answer> getAnswersSet() {
        return answersSet;
    }
}
