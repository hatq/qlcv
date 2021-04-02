package info.dt.qlcv.repository;

import org.springframework.data.repository.CrudRepository;

import info.dt.qlcv.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
