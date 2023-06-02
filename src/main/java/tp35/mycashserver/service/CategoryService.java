package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.dto.CategoryDTO;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.mapper.UserMapper;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;
    private final BaseCategoryService baseCategoryService;

    public List<Category> getAllUserCategories(User user) {
        return categoryMapper.toCategories(categoryRepository.findAllByUser(userMapper.toUserEntity(user)));
    }

    public Category getCategoryByUserAndName(User user, String name) {
        return categoryMapper.toCategory(categoryRepository.findByUserAndBaseCategory_Name(userMapper.toUserEntity(user), name));
    }

    public void addCategory(Category category) {
        categoryRepository.save(categoryMapper.toCategoryEntity(category));
    }

    public void updateCategory(User user, CategoryDTO categoryDTO) {
        Category category = getCategoryByUserAndName(user, categoryDTO.getName());
        category.setColor(categoryDTO.getColor());
        category.setIsLimited(categoryDTO.getIsLimited());
        category.setSpendingLimit(categoryDTO.getSpendingLimit());
        addCategory(category);
    }

    public void addUserCategoriesFormBaseCategories(User user) {
        List<BaseCategory> baseCategories = baseCategoryService.getAllBaseCategories();
        List<Category> userCategories = baseCategories.stream().map(
                baseCategory -> new Category(null, baseCategory, user, false, null, baseCategory.getColor())
        ).toList();
        categoryRepository.saveAll(categoryMapper.toCategoryEntities(userCategories));
    }
}
