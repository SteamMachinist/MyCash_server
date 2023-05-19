package tp35.mycashserver.repository;

import org.springframework.data.repository.CrudRepository;
import tp35.mycashserver.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByToken(String token);
}
