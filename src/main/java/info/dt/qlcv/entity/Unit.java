package info.dt.qlcv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@Entity
public class Unit {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	
	@Column(name = "ma_don_vi")
	private String maDonVi;
	
	@Column(name = "ten_don_vi")
	private String tenDonVi;
	
	@Column(name = "loai_don_vi")
	private Integer loaiDonVi;
	
	@Column(name = "ma_don_vi_cha")
	private String maDonViCha;
	
	@Column(name = "dia_chi")
	private String diaChi;
	
	@Column(name = "kinh_do")
	private Float kinhDo;
	
	@Column(name = "vi_do")
	private Float viDo;
	
	@Column(name = "thoi_gian_tao")
	private Date thoiGianTao;
	
	@Column(name = "thoi_gian_cap_nhat")
	private Date thoiGianCapNhat;
	
	@PrePersist
	protected void onCreate() {
		thoiGianTao = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		thoiGianCapNhat = new Date();
	}
	
}
