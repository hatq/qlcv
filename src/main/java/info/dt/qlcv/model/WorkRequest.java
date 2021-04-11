package info.dt.qlcv.model;


import lombok.Data;

@Data
public class WorkRequest {

	private Integer id;
	
	private Integer idDonVi;
	
	private Integer sttCongViec;
	
	private String noiDungCv;
	
	private Integer idUser;
	
	private Integer idUserThucHien;
	
	private String timeThucHien;
	
	private String timeHoanThanh;
	
	private String[] bgd;
	
	private String[] nsth;
	
	private String[] ktkh;
	
	private String[] ktdt;
	
	private String[] dhtt;
	
	private String[] cntt;
	
	private String[] ttvt;
	
	private String ketQua;
	
	
}
