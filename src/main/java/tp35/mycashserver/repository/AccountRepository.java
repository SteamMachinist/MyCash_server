package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.entity.UserEntity;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findAllByOwner(UserEntity owner);
    AccountEntity findByOwnerAndName(UserEntity owner, String name);
}
