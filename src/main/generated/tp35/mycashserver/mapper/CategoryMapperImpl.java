package tp35.mycashserver.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp35.mycashserver.dto.CategoryDTO;
import tp35.mycashserver.entity.CategoryEntity;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.model.CategoryType;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-05T20:56:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BaseCategoryMapper baseCategoryMapper;

    @Override
    public CategoryEntity toCategoryEntity(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( category.getId() );
        categoryEntity.setBaseCategory( baseCategoryMapper.toBaseCategoryEntity( category.getBaseCategory() ) );
        categoryEntity.setUser( userMapper.toUserEntity( category.getUser() ) );
        categoryEntity.setIsLimited( category.getIsLimited() );
        categoryEntity.setSpendingLimit( category.getSpendingLimit() );
        categoryEntity.setColor( category.getColor() );

        return categoryEntity;
    }

    @Override
    public Category toCategory(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryEntity.getId() );
        category.setBaseCategory( baseCategoryMapper.toBaseCategory( categoryEntity.getBaseCategory() ) );
        category.setUser( userMapper.toUser( categoryEntity.getUser() ) );
        category.setIsLimited( categoryEntity.getIsLimited() );
        category.setSpendingLimit( categoryEntity.getSpendingLimit() );
        category.setColor( categoryEntity.getColor() );

        return category;
    }

    @Override
    public CategoryDTO toCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setName( categoryBaseCategoryName( category ) );
        categoryDTO.setType( categoryBaseCategoryType( category ) );
        categoryDTO.setIsLimited( category.getIsLimited() );
        categoryDTO.setSpendingLimit( category.getSpendingLimit() );
        categoryDTO.setColor( category.getColor() );

        return categoryDTO;
    }

    @Override
    public List<CategoryEntity> toCategoryEntities(List<Category> category) {
        if ( category == null ) {
            return null;
        }

        List<CategoryEntity> list = new ArrayList<CategoryEntity>( category.size() );
        for ( Category category1 : category ) {
            list.add( toCategoryEntity( category1 ) );
        }

        return list;
    }

    @Override
    public List<Category> toCategories(List<CategoryEntity> categoryEntities) {
        if ( categoryEntities == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryEntities.size() );
        for ( CategoryEntity categoryEntity : categoryEntities ) {
            list.add( toCategory( categoryEntity ) );
        }

        return list;
    }

    @Override
    public List<CategoryDTO> toCategoryDTOs(List<Category> category) {
        if ( category == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( category.size() );
        for ( Category category1 : category ) {
            list.add( toCategoryDTO( category1 ) );
        }

        return list;
    }

    private String categoryBaseCategoryName(Category category) {
        if ( category == null ) {
            return null;
        }
        BaseCategory baseCategory = category.getBaseCategory();
        if ( baseCategory == null ) {
            return null;
        }
        String name = baseCategory.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private CategoryType categoryBaseCategoryType(Category category) {
        if ( category == null ) {
            return null;
        }
        BaseCategory baseCategory = category.getBaseCategory();
        if ( baseCategory == null ) {
            return null;
        }
        CategoryType type = baseCategory.getType();
        if ( type == null ) {
            return null;
        }
        return type;
    }
}
