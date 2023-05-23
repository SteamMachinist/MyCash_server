package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.entity.OperationEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    List<OperationEntity> findAllByAccount(AccountEntity account);

//    default List<OperationEntity> findAllByAccountAndYearMonth(AccountEntity account, LocalDateTime from, LocalDateTime to) {
//        return findAllByAccountAndCreatedBetween(account, from, to);
//    }
//
//    default List<OperationEntity> findAllByAccountAndYearMonthDate(AccountEntity account, LocalDateTime from, LocalDateTime to) {
//        return
//    }

    List<OperationEntity> findAllByAccountAndCreatedBetween(AccountEntity account, LocalDateTime from, LocalDateTime to);

    //    @Query("SELECT ALL from operation ")
//    List<OperationEntity> findAllByAccountAndMonth();
}
