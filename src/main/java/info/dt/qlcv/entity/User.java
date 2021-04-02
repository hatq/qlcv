package info.dt.qlcv.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;

    @ManyToOne
    @JoinColumn()
    private Role  role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Employee employee;

    @OneToOne(mappedBy = "user")
    private AccessToken accessToken;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "userName")
    private String userName;

    @JoinColumn(name = "password")
    private String password;

}
