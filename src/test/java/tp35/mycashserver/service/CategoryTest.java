package tp35.mycashserver.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tp35.mycashserver.IntegrationEnvironment;
import tp35.mycashserver.mapper.BaseCategoryMapper;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.mapper.UserMapper;
import tp35.mycashserver.model.*;
import tp35.mycashserver.repository.BaseCategoryRepository;
import tp35.mycashserver.repository.CategoryRepository;
import tp35.mycashserver.repository.UserRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Rollback
public class CategoryTest extends IntegrationEnvironment {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BaseCategoryRepository baseCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BaseCategoryMapper baseCategoryMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    @Transactional
    public void testAddCategory() {
        User user = new User(5L, "testname", "password123", Role.UNREGISTERED);
        userRepository.saveAndFlush(userMapper.toUserEntity(user));

        BaseCategory baseCategory = new BaseCategory(5L, "base category", CategoryType.INCOME, 123);
        baseCategoryRepository.saveAndFlush(baseCategoryMapper.toBaseCategoryEntity(baseCategory));

        Category category = new Category(5L, baseCategory, user, true, 5000.0, 123);
        categoryRepository.saveAndFlush(categoryMapper.toCategoryEntity(category));

        List<Category> testedCategories = categoryService.getAllUserCategories(user);

        assertThat(testedCategories.size(), is(1));
        assertThat(testedCategories.get(0).getBaseCategory().getName(), is(baseCategory.getName()));
        assertThat(testedCategories.get(0).getUser().getUsername(), is(user.getUsername()));
        assertThat(testedCategories.get(0).getSpendingLimit(), is(5000.0));
    }
}
