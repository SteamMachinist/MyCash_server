package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import tp35.mycashserver.entity.CategoryEntity;
import tp35.mycashserver.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryToEntityMapper {
    CategoryEntity toCategoryEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
}
