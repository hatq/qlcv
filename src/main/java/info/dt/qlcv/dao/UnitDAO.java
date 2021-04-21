package info.dt.qlcv.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.dt.qlcv.entity.Unit;
import info.dt.qlcv.model.UnitRequest;
import info.dt.qlcv.repository.UnitRepository;

@Service
public class UnitDAO {

	@Autowired
	private UnitRepository unitRepo;
	
	public List<Unit> findAll() {
		return this.unitRepo.findAll();
	}
	
	public Optional<Unit> findById(Integer id) {
		return this.unitRepo.findById(id);
	}
	
	public String taoMaDonVi() {
    	long count = unitRepo.count();
    	String formatted = String.format("DV%03d", count+1);
    	return formatted;
    }
    
    public String addUnit(UnitRequest unitRequest) {
    	try {
    		if (!unitRepo.existsUnitByTenDonVi(unitRequest.getTenDonVi())) {
				Unit unit = new Unit();
				unit.setMaDonVi(unitRequest.getMaDonVi());
				unit.setTenDonVi(unitRequest.getTenDonVi());
				unit.setLoaiDonVi(unitRequest.getLoaiDonVi());
				unit.setMaDonViCha(unitRequest.getMaDonViCha());
				unit.setDiaChi(unitRequest.getDiaChi());
				unit.setThoiGianTao(new Date());
				unit.setThoiGianCapNhat(new Date());
				
				this.unitRepo.save(unit);
				return "True";
			}
    		return "False1";
			
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    
    public String editUnit(UnitRequest unitRequest) {
    	try {
			Optional<Unit> oUnit = unitRepo.findById(unitRequest.getId());
			if(oUnit.isPresent()) {

                Unit unit = oUnit.get();
                if(!unit.getTenDonVi().equals(unitRequest.getTenDonVi()) &&
                		unitRepo.existsUnitByTenDonVi(unitRequest.getTenDonVi())){
                    return "Da ton tai don vi.";
                }
                unit.setTenDonVi(unitRequest.getTenDonVi());
                unit.setLoaiDonVi(unitRequest.getLoaiDonVi());
                unit.setMaDonViCha(unitRequest.getMaDonViCha());
                unit.setDiaChi(unitRequest.getDiaChi());

                unitRepo.save(unit);

                return "True";
            }
            else
            {
                return "Khong tim thay don vi.";
            }
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    
    public boolean delete(Integer id){
        try {
            Optional<Unit> unit = unitRepo.findById(id);
            if(unit.isPresent()) {
            	unitRepo.delete(unit.get());
                return true;
            }
            return false;
        }
        catch (Exception e){
            return false;
        }
    }
}
