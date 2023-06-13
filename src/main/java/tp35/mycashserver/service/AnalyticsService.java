package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.model.User;
import tp35.mycashserver.response.AllMonthData;
import tp35.mycashserver.response.AnalyticsResponse;
import tp35.mycashserver.response.CategoriesDaySum;
import tp35.mycashserver.utils.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final OperationSumGetterService operationSumGetterService;

    public List<CategoriesDaySum> getDayCategoriesSums(LocalDate begin, LocalDate end,
                                                       List<Category> categories, Account account,
                                                       int year, int month) {

        return IntStream.range(begin.getDayOfMonth(), end.getDayOfMonth() + 1)
                .mapToObj(day ->
                        new CategoriesDaySum(
                                day,
                                categories.stream()
                                        .map(category ->
                                                new Pair<>(
                                                        categoryMapper.toCategoryDTO(category),
                                                        operationSumGetterService.getOperationSumBy(
                                                                account,
                                                                category,
                                                                year,
                                                                month,
                                                                day)))
                                        .collect(Collectors.toList())))
                .toList();
    }

    public AllMonthData getAllMonthData(Account account, int year, int month) {
        List<LocalDate> months = new ArrayList<>(IntStream.range(0, 6)
                .mapToObj(value -> LocalDate.now().withYear(year).withMonth(month).minusMonths(value)).toList());
        Collections.reverse(months);

        List<Double> incomes = months.stream()
                .map(localDate -> operationSumGetterService
                        .getOperationSumBy(
                                account,
                                CategoryType.INCOME,
                                localDate.getYear(),
                                localDate.getMonthValue()))
                .collect(Collectors.toList());

        List<Double> expenses = months.stream()
                .map(localDate -> operationSumGetterService
                        .getOperationSumBy(
                                account,
                                CategoryType.EXPENSE,
                                localDate.getYear(),
                                localDate.getMonthValue()))
                .collect(Collectors.toList());

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

        return new AllMonthData(account.getSpendingLimit(), account.getTarget(), balanceMonths, incomes, expenses);
    }

    public AnalyticsResponse getAnalyticsFor(User user, Account account, int year, int month) {
        List<Category> expensesCategories = categoryService.getUserCategoriesByType(user, CategoryType.EXPENSE);
        List<Category> incomesCategories = categoryService.getUserCategoriesByType(user, CategoryType.INCOME);

        LocalDate begin = LocalDate.now().withYear(year).withMonth(month).withDayOfMonth(1);
        LocalDate end = LocalDate.now().withYear(year).withMonth(month).withDayOfMonth(begin.lengthOfMonth());

        List<CategoriesDaySum> incomes = getDayCategoriesSums(begin, end, incomesCategories, account, year, month);
        List<CategoriesDaySum> expenses = getDayCategoriesSums(begin, end, expensesCategories, account, year, month);

        AllMonthData allMonthData = getAllMonthData(account, year, month);

        return new AnalyticsResponse(incomes, expenses, allMonthData);
    }
}