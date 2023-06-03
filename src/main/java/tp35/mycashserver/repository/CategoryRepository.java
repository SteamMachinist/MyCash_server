package tp35.mycashserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp35.mycashserver.entity.BaseCategoryEntity;
import tp35.mycashserver.entity.CategoryEntity;
import tp35.mycashserver.entity.UserEntity;
import tp35.mycashserver.model.CategoryType;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByUser(UserEntity userEntity);

    CategoryEntity findByUserAndBaseCategory_Name(UserEntity userEntity, String name);

    List<CategoryEntity> findAllByUserAndBaseCategory(UserEntity user, BaseCategoryEntity baseCategory);

    List<CategoryEntity> findAllByUserAndBaseCategory_Type(UserEntity userEntity, CategoryType categoryType);
}
