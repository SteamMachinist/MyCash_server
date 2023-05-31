package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tp35.mycashserver.dto.CategoryDTO;
import tp35.mycashserver.entity.CategoryEntity;
import tp35.mycashserver.model.Category;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class, BaseCategoryMapper.class})
public interface CategoryMapper {
    CategoryEntity toCategoryEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);

    @Mapping(source = "baseCategory.name", target = "name")
    @Mapping(source = "baseCategory.type", target = "type")
    CategoryDTO toCategoryDTO(Category category);


    List<CategoryEntity> toCategoryEntities(List<Category> category);

    List<Category> toCategories(List<CategoryEntity> categoryEntities);

    List<CategoryDTO> toCategoryDTOs(List<Category> category);
}
