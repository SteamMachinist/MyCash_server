package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import tp35.mycashserver.entity.BaseCategoryEntity;
import tp35.mycashserver.model.BaseCategory;

@Mapper(componentModel = "spring")
public interface BaseCategoryToEntityMapper {
    BaseCategoryEntity toBaseCategoryEntity(BaseCategory baseCategory);
    BaseCategory toBaseCategory(BaseCategoryEntity baseCategoryEntity);
}
