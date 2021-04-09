package info.dt.qlcv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@Entity
@SuperBuilder
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class Work {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	
	@Column(name = "id_don_vi")
	private Integer idDonVi;
	
	@Column(name = "stt")
	private Integer stt;
	
	@Column(name = "noi_dung_cv")
	private String noiDungCv;
	
	@Column(name = "id_user")
	private Integer idUser;
	
	@Column(name = "id_user_thuc_hien")
	private Integer idUserThucHien;
	
	@Column(name = "time_thuc_hien")
	private Date timeThucHien;
	
	@Column(name = "time_hoan_thanh")
	private Date timeHoanThanh;
	
	@Column(name = "bgd")
	private String bgd;
	
	@Column(name = "nsth")
	private String nsth;
	
	@Column(name = "ktkh")
	private String ktkh;
	
	@Column(name = "ktdt")
	private String ktdt;
	
	@Column(name = "dhtt")
	private String dhtt;
	
	@Column(name = "cntt")
	private String cntt;
	
	@Column(name = "ttvt")
	private String ttvt;
	
	@Column(name = "ket_qua")
	private String ketQua;
	
	@Column(name = "create_time")
    private Date createTime;
    
    @Column(name = "update_time")
    private Date updateTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user", referencedColumnName = "idUser", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user_thuc_hien", referencedColumnName = "idUser", insertable = false, updatable = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private User userThucHien;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_don_vi", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
	private Unit unit;
	
	@PrePersist
	protected void onCreate() {
    	createTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updateTime = new Date();
	}
}
