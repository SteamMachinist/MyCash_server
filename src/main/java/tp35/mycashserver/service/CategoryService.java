package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.CategoryToEntityMapper;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryToEntityMapper categoryToEntityMapper;

    public void add(Category category) {
        categoryRepository.save(categoryToEntityMapper.toCategoryEntity(category));
    }
}
