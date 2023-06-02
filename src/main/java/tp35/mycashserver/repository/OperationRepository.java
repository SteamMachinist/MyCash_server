package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.entity.OperationEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    List<OperationEntity> findAllByAccount(AccountEntity account);

    List<OperationEntity> findAllByAccountAndDateTimeBetween(AccountEntity account, LocalDateTime from, LocalDateTime to);
}
