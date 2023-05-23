package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.entity.UserEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByOwnerAndName(UserEntity owner, String name);
}
