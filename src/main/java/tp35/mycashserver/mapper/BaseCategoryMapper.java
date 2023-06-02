package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import tp35.mycashserver.entity.BaseCategoryEntity;
import tp35.mycashserver.model.BaseCategory;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BaseCategoryMapper {
    BaseCategoryEntity toBaseCategoryEntity(BaseCategory baseCategory);
    BaseCategory toBaseCategory(BaseCategoryEntity baseCategoryEntity);

    List<BaseCategoryEntity> toBaseCategoryEntities(List<BaseCategory> baseCategory);

    List<BaseCategory> toBaseCategories(List<BaseCategoryEntity> baseCategoryEntities);
}
