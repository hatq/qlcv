package info.dt.qlcv.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nameRole")
    private String nameRole;

    @Column(name = "level")
    private int level;

    @Column(name = "status")
    private int status;



    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users;

    public Role() {
    }

    public Role(String nameRole, int status, int level) {
        this.nameRole = nameRole;
        this.status = status;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
