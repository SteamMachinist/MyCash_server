package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.entity.BaseCategoryEntity;
import tp35.mycashserver.entity.CategoryEntity;
import tp35.mycashserver.entity.OperationEntity;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.model.CategoryType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    List<OperationEntity> findAllByAccount(AccountEntity account);

    List<OperationEntity> findAllByAccountAndDateTimeBetween(AccountEntity accountEntity, LocalDateTime from, LocalDateTime to);

    @Query("SELECT SUM(o.value) from OperationEntity o where o.account = ?1 and o.dateTime between ?2 and ?3")
    Optional<Double> sumOfOperationsBy(AccountEntity accountEntity, LocalDateTime from, LocalDateTime to);

    @Query("SELECT SUM(o.value) from OperationEntity o where o.account = ?1 and o.category = ?2 and o.dateTime between ?3 and ?4")
    Optional<Double> sumOfOperationsBy(AccountEntity accountEntity, CategoryEntity categoryEntity, LocalDateTime from, LocalDateTime to);

    @Query("SELECT SUM(o.value) from OperationEntity o where o.account = ?1 and o.category.baseCategory.type = ?2 and o.dateTime between ?3 and ?4")
    Optional<Double> sumOfOperationsBy(AccountEntity accountEntity, CategoryType categoryType, LocalDateTime from, LocalDateTime to);

    @Query("SELECT SUM(o.value) from OperationEntity o where o.category.baseCategory = ?1 and  o.dateTime between ?2 and ?3")
    Optional<Double> sumOfOperationsBy(BaseCategoryEntity baseCategoryEntity, LocalDateTime from, LocalDateTime to);

    @Query("SELECT AVG(o.value) from OperationEntity o where o.category.baseCategory = ?1 and  o.dateTime between ?2 and ?3")
    Optional<Double> averageOfOperationsBy(BaseCategoryEntity baseCategoryEntity, LocalDateTime from, LocalDateTime to);
}
