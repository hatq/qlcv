package info.dt.qlcv.repository;

import org.springframework.data.repository.CrudRepository;

import info.dt.qlcv.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByLevelAndStatus(int level, int status);
}
