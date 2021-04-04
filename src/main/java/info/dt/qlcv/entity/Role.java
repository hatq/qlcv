package info.dt.qlcv.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
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

}
