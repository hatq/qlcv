package info.dt.qlcv.repository;

import org.springframework.data.repository.CrudRepository;

import info.dt.qlcv.entity.AccessToken;
import info.dt.qlcv.entity.User;

import java.util.Optional;

public interface AccessTokenRepository extends CrudRepository<AccessToken, Integer> {

    boolean existsByUserAndToken(User user, String token);

    Optional<AccessToken> findByUserAndToken(User user, String token);

    Optional<AccessToken> findByUser(User user);
}
