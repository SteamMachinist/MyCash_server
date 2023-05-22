package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.BaseCategoryToEntityMapper;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.repository.BaseCategoryRepository;

@Service
@RequiredArgsConstructor
public class BaseCategoryService {
    private final BaseCategoryRepository baseCategoryRepository;
    private final BaseCategoryToEntityMapper baseCategoryToEntityMapper;

    public void add(BaseCategory baseCategory) {
        baseCategoryRepository.save(baseCategoryToEntityMapper.toBaseCategoryEntity(baseCategory));
    }
}
