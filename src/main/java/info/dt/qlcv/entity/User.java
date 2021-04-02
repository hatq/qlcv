package info.dt.qlcv.entity;

import javax.persistence.*;

@Entity
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

    public User() {
    }

    public User(Role role, Employee employee, AccessToken accessToken, String email, String userName, String password) {
        this.role = role;
        this.employee = employee;
        this.accessToken = accessToken;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
