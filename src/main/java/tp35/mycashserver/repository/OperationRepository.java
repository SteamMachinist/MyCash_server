package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
}
