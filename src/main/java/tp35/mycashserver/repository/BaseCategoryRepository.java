package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.BaseCategoryEntity;

public interface BaseCategoryRepository extends JpaRepository<BaseCategoryEntity, Long> {
}
