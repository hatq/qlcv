package info.dt.qlcv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;

    @JoinColumn(name = "user_name")
    private String userName;

    @JoinColumn(name = "password")
    private String password;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    private String email;
    
    private String phone;
    
    @Column(name = "don_vi")
    private String donVi;
    
    private Integer status;
    
    @Column(name = "role_id")
    private Integer roleId;
    
    @Column(name = "create_time")
    private Date createTime;
    
    @Column(name = "update_time")
    private Date updateTime;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Role role;

    @OneToOne(mappedBy = "user")
    private AccessToken accessToken;
    
    @PrePersist
	protected void onCreate() {
    	createTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updateTime = new Date();
	}

}
