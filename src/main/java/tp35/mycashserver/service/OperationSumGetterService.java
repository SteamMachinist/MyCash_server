package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.model.Operation;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationSumGetterService {
    private final OperationsGetterService operationsGetterService;

    public Double getSumOperationsByAccountByDate(Account account, int year) {
        return operationsGetterService.getOperationsByAccountByDate(account, year).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

    public Double getSumOperationsByAccountByCategoryByDate(Account account, Category category, int year) {
        return operationsGetterService.getOperationsByAccountByCategoryByDate(account, category, year).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

    public Double getSumOperationsByAccountByCategoryTypeByDate(Account account, CategoryType categoryType, int year) {
        return operationsGetterService.getOperationsByAccountByCategoryTypeByDate(account, categoryType, year).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

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

    public Double getSumOperationsByAccountByCategoryByDate(Account account, Category category, int year, int month, int day) {
        return operationsGetterService.getOperationsByAccountByCategoryByDate(account, category, year, month, day).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

    public Double getSumOperationsByAccountByCategoryTypeByDate(Account account, CategoryType categoryType, int year, int month, int day) {
        return operationsGetterService.getOperationsByAccountByCategoryTypeByDate(account, categoryType, year, month, day).stream()
                .map(Operation::getValue).reduce(Double::sum).orElse(0.0);
    }

    public List<Double> getSumByTypeByMonthList(List<LocalDate> months, Account account, CategoryType categoryType) {
        return months.stream()
                .map(localDate ->
                        getSumOperationsByAccountByCategoryTypeByDate(
                                account,
                                categoryType,
                                localDate.getYear(),
                                localDate.getMonthValue()))
                .toList();
    }
}
