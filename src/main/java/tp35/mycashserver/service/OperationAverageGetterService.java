package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.BaseCategoryMapper;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.repository.OperationRepository;
import tp35.mycashserver.utils.DateUtils;
import tp35.mycashserver.utils.Pair;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OperationAverageGetterService {
    private final OperationRepository operationRepository;
    private final BaseCategoryMapper baseCategoryMapper;

    public Double getOperationsAverageBy(BaseCategory baseCategory, int year, int month) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getBorderingDatesBetween(year, month, year, month);
        return operationRepository.averageOfOperationsBy(baseCategoryMapper.toBaseCategoryEntity(baseCategory),
                monthDates.getFirst(), monthDates.getSecond()).orElse(0.0);
    }
}
