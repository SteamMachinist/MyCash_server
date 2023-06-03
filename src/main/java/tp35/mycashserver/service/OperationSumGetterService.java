package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.model.Operation;

@Service
@RequiredArgsConstructor
public class OperationSumGetterService {
    private final OperationsGetterService operationsGetterService;

    public Double getSumOperationsByAccountByDate(Account account, int year, int month) {
        return operationsGetterService.getOperationsByAccountByDate(account, year, month).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

    public Double getSumOperationsByAccountByCategoryByDate(Account account, Category category, int year, int month) {
        return operationsGetterService.getOperationsByAccountByCategoryByDate(account, category, year, month).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

    public Double getSumOperationsByAccountByCategoryTypeByDate(Account account, CategoryType categoryType, int year, int month) {
        return operationsGetterService.getOperationsByAccountByCategoryTypeByDate(account, categoryType, year, month).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

    public Double getSumOperationsByAccountByDate(Account account, int year, int month, int day) {
        return operationsGetterService.getOperationsByAccountByDate(account, year, month, day).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

    public Double getSumOperationsByAccountByCategoryByDate(Account account, int year, Category category, int month, int day) {
        return operationsGetterService.getOperationsByAccountByCategoryByDate(account, category, year, month, day).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

    public Double getSumOperationsByAccountByCategoryTypeByDate(Account account, CategoryType categoryType, int year, int month, int day) {
        return operationsGetterService.getOperationsByAccountByCategoryTypeByDate(account, categoryType, year, month, day).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }
}
