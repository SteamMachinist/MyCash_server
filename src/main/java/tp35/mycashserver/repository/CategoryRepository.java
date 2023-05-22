package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
