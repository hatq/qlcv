package info.dt.qlcv.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.dt.qlcv.entity.Department;
import info.dt.qlcv.entity.Unit;
import info.dt.qlcv.model.DepartmentRequest;
import info.dt.qlcv.model.UnitRequest;
import info.dt.qlcv.repository.DepartmentRepository;
import info.dt.qlcv.repository.UnitRepository;

@Service
public class DepartmentDAO {
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private UnitRepository unitRepo;


    public Department getDepartmentById(int IdDepartment){
        Optional<Department> departmentOptional = departmentRepository.findById(IdDepartment);
        return departmentOptional.orElse(null);

    }

    public String addDepartment(DepartmentRequest departmentRequest)
    {
        try {
            if(!departmentRepository.existsDepartmentByNameDepartment(departmentRequest.getNameDepartment()))
            {
                Department department = new Department();
                department.setNameDepartment(departmentRequest.getNameDepartment());
                department.setStatus(0);

                departmentRepository.save(department);

                return "True";
            }
            else
            {
                return "Department Name exist !";
            }
        }catch (Exception ex)
        {
            return ex.getMessage();
        }
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
    		return "False";
			
		} catch (Exception e) {
			return e.getMessage();
		}
    }

    public String editDepartment(DepartmentRequest departmentRequest){
        try {
            Optional<Department> departmentOptional = departmentRepository.findById(departmentRequest.getIdDepartment());
            if(departmentOptional.isPresent()) {

                Department department = departmentOptional.get();
                if(!department.getNameDepartment().equals(departmentRequest.getNameDepartment()) &&
                        departmentRepository.existsDepartmentByNameDepartment(departmentRequest.getNameDepartment())){
                    return "Department name is exist";
                }
                department.setIdDepartment(departmentRequest.getIdDepartment());
                department.setNameDepartment(departmentRequest.getNameDepartment());

                departmentRepository.save(department);

                return "True";
            }
            else
            {
                return "Department not found";
            }
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }

    public List<Department> getAllDepartment()
    {
        List<Department> lstDepartment = (List<Department>) departmentRepository.findAll();
        if(lstDepartment != null && lstDepartment.size() > 0)
        {
            return lstDepartment;
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public boolean delete(int idDepartment){
        try {
            Optional<Department> department = departmentRepository.findById(idDepartment);
            if(department.isPresent()) {
                departmentRepository.delete(department.get());
                return true;
            }
            return false;
        }
        catch (Exception e){
            return false;
        }
    }
}
