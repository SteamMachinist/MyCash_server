package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.mapper.UserMapper;
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

    //public void addUserCategory()

    public List<Category> getAllUserCategories(User user) {
        return categoryMapper.toCategories(categoryRepository.findAllByUser(userMapper.toUserEntity(user)));
    }

    public void addCategory(Category category) {
        categoryRepository.save(categoryMapper.toCategoryEntity(category));
    }
}
