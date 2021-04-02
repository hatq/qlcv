package info.dt.qlcv.repository;

import org.springframework.data.repository.CrudRepository;

import info.dt.qlcv.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department,Integer> {
    Boolean existsDepartmentByNameDepartment(String nameDepartment);
}
