package info.dt.qlcv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.dt.qlcv.entity.Department;
import info.dt.qlcv.model.DepartmentRequest;
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
