package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.BaseCategoryMapper;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.repository.BaseCategoryRepository;

@Service
@RequiredArgsConstructor
public class BaseCategoryService {
    private final BaseCategoryRepository baseCategoryRepository;
    private final BaseCategoryMapper baseCategoryMapper;

    public void add(BaseCategory baseCategory) {
        baseCategoryRepository.save(baseCategoryMapper.toBaseCategoryEntity(baseCategory));
    }
}
