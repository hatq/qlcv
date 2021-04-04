package info.dt.qlcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Raci {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ki_hieu")
	private String kiHieu;
	
	@Column(name = "ten")
	private String ten;
	
	@Column(name = "mo_ta")
	private String moTa;
}
