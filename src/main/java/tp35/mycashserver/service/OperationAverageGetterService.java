package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.model.Operation;

@Service
@RequiredArgsConstructor
public class OperationAverageGetterService {
    private final OperationsGetterService operationsGetterService;

    public Double getAverageOperationsByBaseCategoryByDate(BaseCategory baseCategory, int year, int month) {
        return operationsGetterService.getOperationsByBaseCategoryByDate(baseCategory, year, month).stream()
                .mapToDouble(Operation::getValue).average().orElse(0.0);
    }
}
