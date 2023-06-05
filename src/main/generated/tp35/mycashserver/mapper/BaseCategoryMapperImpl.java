package tp35.mycashserver.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp35.mycashserver.entity.BaseCategoryEntity;
import tp35.mycashserver.model.BaseCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-05T20:56:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class BaseCategoryMapperImpl implements BaseCategoryMapper {

    @Override
    public BaseCategoryEntity toBaseCategoryEntity(BaseCategory baseCategory) {
        if ( baseCategory == null ) {
            return null;
        }

        BaseCategoryEntity baseCategoryEntity = new BaseCategoryEntity();

        baseCategoryEntity.setId( baseCategory.getId() );
        baseCategoryEntity.setName( baseCategory.getName() );
        baseCategoryEntity.setType( baseCategory.getType() );
        baseCategoryEntity.setColor( baseCategory.getColor() );

        return baseCategoryEntity;
    }

    @Override
    public BaseCategory toBaseCategory(BaseCategoryEntity baseCategoryEntity) {
        if ( baseCategoryEntity == null ) {
            return null;
        }

        BaseCategory baseCategory = new BaseCategory();

        baseCategory.setId( baseCategoryEntity.getId() );
        baseCategory.setName( baseCategoryEntity.getName() );
        baseCategory.setType( baseCategoryEntity.getType() );
        baseCategory.setColor( baseCategoryEntity.getColor() );

        return baseCategory;
    }

    @Override
    public List<BaseCategoryEntity> toBaseCategoryEntities(List<BaseCategory> baseCategory) {
        if ( baseCategory == null ) {
            return null;
        }

        List<BaseCategoryEntity> list = new ArrayList<BaseCategoryEntity>( baseCategory.size() );
        for ( BaseCategory baseCategory1 : baseCategory ) {
            list.add( toBaseCategoryEntity( baseCategory1 ) );
        }

        return list;
    }

    @Override
    public List<BaseCategory> toBaseCategories(List<BaseCategoryEntity> baseCategoryEntities) {
        if ( baseCategoryEntities == null ) {
            return null;
        }

        List<BaseCategory> list = new ArrayList<BaseCategory>( baseCategoryEntities.size() );
        for ( BaseCategoryEntity baseCategoryEntity : baseCategoryEntities ) {
            list.add( toBaseCategory( baseCategoryEntity ) );
        }

        return list;
    }
}
