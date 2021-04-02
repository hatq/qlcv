package info.dt.qlcv.entity;

import javax.persistence.*;

@Entity
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn
    private User user;

    @Column(name = "token")
    private String token;

    @Column(name = "status")
    private int status;

    public AccessToken() {
    }

    public AccessToken(User user, String token, int status) {
        this.user = user;
        this.token = token;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
