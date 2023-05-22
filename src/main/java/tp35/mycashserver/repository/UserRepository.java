package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
