package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {}
