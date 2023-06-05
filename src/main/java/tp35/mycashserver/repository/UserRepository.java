package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.UserEntity;
import tp35.mycashserver.model.Role;

import java.util.Set;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    int countByRolesContaining(Role role);
}
