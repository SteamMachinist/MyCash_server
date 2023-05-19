package tp35.mycashserver.repository;

import org.springframework.data.repository.CrudRepository;
import tp35.mycashserver.entity.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {}
