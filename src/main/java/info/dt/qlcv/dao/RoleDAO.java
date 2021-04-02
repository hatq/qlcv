package info.dt.qlcv.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.dt.qlcv.entity.Role;
import info.dt.qlcv.repository.RoleRepository;

@Service
public class RoleDAO {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRole(){
        return (List<Role>) roleRepository.findAll();
    }
    public Role getListRoleById(int id){
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }
}
