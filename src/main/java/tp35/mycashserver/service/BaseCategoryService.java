package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.BaseCategoryMapper;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.repository.BaseCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseCategoryService {
    private final BaseCategoryRepository baseCategoryRepository;
    private final BaseCategoryMapper baseCategoryMapper;

    public void addBaseCategory(BaseCategory baseCategory) {
        baseCategoryRepository.save(baseCategoryMapper.toBaseCategoryEntity(baseCategory));
    }

    public void addBaseCategories(List<BaseCategory> baseCategories) {
        baseCategoryRepository.saveAll(baseCategoryMapper.toBaseCategoryEntities(baseCategories));
    }

    public List<BaseCategory> getAllBaseCategories() {
        return baseCategoryMapper.toBaseCategories(baseCategoryRepository.findAll());
    }

}
