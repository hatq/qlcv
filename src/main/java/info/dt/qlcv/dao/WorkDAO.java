package info.dt.qlcv.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.dt.qlcv.entity.Work;
import info.dt.qlcv.model.WorkRequest;
import info.dt.qlcv.repository.WorkRepository;

@Service
public class WorkDAO {

	@Autowired
	private WorkRepository workRepo;
	
	public List<Work> findAll() {
		return this.workRepo.findAll();
	}
	
	public Optional<Work> findById(Integer id) {
		return this.workRepo.findById(id);
	}
	
	public Work save(WorkRequest workRequest) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date timeTH = formatter.parse(workRequest.getTimeThucHien());
		Date timeHT = formatter.parse(workRequest.getTimeHoanThanh());
		
		Work work = Work.builder()
				.idDonVi(workRequest.getIdDonVi())
				.stt(workRequest.getSttCongViec())
				.noiDungCv(workRequest.getNoiDungCv())
				.idUser(workRequest.getIdUser())
				.idUserThucHien(workRequest.getIdUserThucHien())
				.timeThucHien(timeTH)
				.timeHoanThanh(timeHT)
				.bgd(convertRACI(workRequest.getBgd()))
				.nsth(convertRACI(workRequest.getNsth()))
				.ktkh(convertRACI(workRequest.getKtkh()))
				.ktdt(convertRACI(workRequest.getKtdt()))
				.dhtt(convertRACI(workRequest.getDhtt()))
				.cntt(convertRACI(workRequest.getCntt()))
				.ttvt(convertRACI(workRequest.getTtvt()))
				.ketQua(workRequest.getKetQua())
				.build();
		
		return workRepo.save(work);
	}
	
	public boolean delete(Integer id){
        try {
        	workRepo.deleteById(id);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
	
	private String convertRACI(String[] arr) {
		StringBuilder str = new StringBuilder();
		if (arr != null) {
			for (String string : arr) {
				str.append(string);
			}
			return str.toString();
		}
		return null;
	}
}
