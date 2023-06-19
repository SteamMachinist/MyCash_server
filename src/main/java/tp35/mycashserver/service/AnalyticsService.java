package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.model.*;
import tp35.mycashserver.response.AllMonthData;
import tp35.mycashserver.response.AnalyticsResponse;
import tp35.mycashserver.response.CategoriesDaySum;
import tp35.mycashserver.utils.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final OperationsGetterService operationsGetterService;

    public List<CategoriesDaySum> getDayCategoriesSums(LocalDate begin, LocalDate end,
                                                       List<Category> categories, List<Operation> operations) {

        return IntStream.range(begin.getDayOfMonth(), end.getDayOfMonth() + 1)
                .mapToObj(day ->
                        new CategoriesDaySum(
                                day,
                                categories.stream()
                                        .map(category ->
                                                new Pair<>(
                                                        categoryMapper.toCategoryDTO(category),
                                                        operations.stream()
                                                                .filter(operation -> operation.getCategory().getBaseCategory().getName()
                                                                        .equals(category.getBaseCategory().getName()))
                                                                .filter(operation -> operation.getDateTime().getDayOfMonth() == day)
                                                                .mapToDouble(Operation::getValue)
                                                                .reduce(Double::sum).orElse(0.0)))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public AllMonthData getAllMonthData(Account account, int year, int month, List<Operation> operations) {
        List<LocalDate> months = new ArrayList<>(IntStream.range(0, 6)
                .mapToObj(value -> LocalDate.now().withYear(year).withMonth(month).minusMonths(value)).toList());
        Collections.reverse(months);

        List<Double> incomes = getSumsByType(months, operations, CategoryType.INCOME);
        List<Double> expenses = getSumsByType(months, operations, CategoryType.EXPENSE);

        int n = incomes.size();
        List<Double> balanceMonths = new ArrayList<>();
        balanceMonths.add(account.getBalance());
        for (int i = 0; i < n; i++) {
            double current = balanceMonths.get(i);
            current -= incomes.get(n - 1- i);
            current += expenses.get(n - 1 - i);
            balanceMonths.add(current);
        }
        Collections.reverse(balanceMonths);
        balanceMonths.remove(0);

        return new AllMonthData(account.getSpendingLimit(), account.getTarget(), balanceMonths, incomes, expenses);
    }

    public List<Double> getSumsByType(List<LocalDate> months, List<Operation> operations, CategoryType type) {
        return months.stream()
                .map(localDate -> operations.stream()
                        .filter(operation ->
                                operation.getDateTime().getMonthValue() == localDate.getMonthValue() &&
                                        operation.getCategory().getBaseCategory().getType() == type)
                        .mapToDouble(Operation::getValue)
                        .reduce(Double::sum).orElse(0.0))
                .collect(Collectors.toList());
    }

    public AnalyticsResponse getAnalyticsFor(User user, Account account, int year, int month) {
        List<Category> expensesCategories = categoryService.getUserCategoriesByType(user, CategoryType.EXPENSE);
        List<Category> incomesCategories = categoryService.getUserCategoriesByType(user, CategoryType.INCOME);

        LocalDate begin = LocalDate.now().withYear(year).withMonth(month).withDayOfMonth(1);
        LocalDate end = LocalDate.now().withYear(year).withMonth(month).withDayOfMonth(begin.lengthOfMonth());

        List<Operation> operations = operationsGetterService.getOperationsByAccountByDate(account, year, month);

        List<CategoriesDaySum> incomes = getDayCategoriesSums(begin, end, incomesCategories, operations);
        List<CategoriesDaySum> expenses = getDayCategoriesSums(begin, end, expensesCategories, operations);

        AllMonthData allMonthData = getAllMonthData(account, year, month, operations);

        return new AnalyticsResponse(incomes, expenses, allMonthData);
    }
}