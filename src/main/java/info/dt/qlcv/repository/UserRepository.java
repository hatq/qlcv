package info.dt.qlcv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import info.dt.qlcv.entity.User;

@Service
public interface UserRepository extends CrudRepository<User, Integer> {
    User findTopByUserNameOrEmail(String userName, String email);

    Optional<User> findTopByUserNameOrEmailAndPassword(String userName, String email, String password);
    
    List<User> findByRoleIdGreaterThanEqual(Integer roleId);

    boolean existsByUserNameOrEmail(String userName, String email);
    
    boolean existsByUserName(String userName);


}
