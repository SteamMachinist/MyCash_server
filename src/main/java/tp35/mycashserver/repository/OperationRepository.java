package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.entity.CategoryEntity;
import tp35.mycashserver.entity.OperationEntity;
import tp35.mycashserver.model.CategoryType;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    List<OperationEntity> findAllByAccount(AccountEntity account);

    List<OperationEntity> findAllByAccountAndDateTimeBetween(AccountEntity accountEntity, LocalDateTime from, LocalDateTime to);

    List<OperationEntity> findAllByAccountAndCategoryAndDateTimeBetween(AccountEntity accountEntity, CategoryEntity categoryEntity, LocalDateTime from, LocalDateTime to);

    List<OperationEntity> findAllByAccountAndCategory_BaseCategory_TypeAndDateTimeBetween(AccountEntity accountEntity, CategoryType categoryType, LocalDateTime from, LocalDateTime to);
}
