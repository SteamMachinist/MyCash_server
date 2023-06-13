package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.response.OperationAddResponse;
import tp35.mycashserver.response.OverLimitType;

@Service
@RequiredArgsConstructor
public class LimitCheckService {
    private final OperationSumGetterService operationSumGetterService;

    public OperationAddResponse checkLimit(Operation operation) {
        if (operation.getCategory().getBaseCategory().getType() == CategoryType.INCOME) {
            return new OperationAddResponse(OverLimitType.NONE);
        }

        if(operation.getAccount().getIsLimited()) {
            double limit = operation.getAccount().getSpendingLimit();
            double spending = operationSumGetterService.getOperationSumBy(
                    operation.getAccount(),
                    operation.getDateTime().getYear(),
                    operation.getDateTime().getMonthValue());
            if(limit < spending) {
                return new OperationAddResponse(OverLimitType.ACCOUNT);
            }
        }

        if (operation.getCategory().getIsLimited()) {
            double limit = operation.getCategory().getSpendingLimit();
            double spending = operationSumGetterService.getOperationSumBy(
                    operation.getAccount(),
                    operation.getCategory(),
                    operation.getDateTime().getYear(),
                    operation.getDateTime().getMonthValue());
            if(limit < spending) {
                return new OperationAddResponse(OverLimitType.CATEGORY);
            }
        }
        return new OperationAddResponse(OverLimitType.NONE);
    }
}
