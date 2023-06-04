package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.model.User;
import tp35.mycashserver.response.AllMonthData;
import tp35.mycashserver.response.AnalyticsResponse;
import tp35.mycashserver.response.DayCategoriesSum;
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
    private final OperationSumGetterService operationSumGetterService;
    private final CategoryService categoryService;

    public List<DayCategoriesSum> getDayCategoriesSums(LocalDate begin, LocalDate end,
                                                       List<Category> categories, Account account,
                                                       int year, int month) {
        return IntStream
                .range(begin.getDayOfMonth(), end.getDayOfMonth() + 1)
                .mapToObj(day ->
                        new DayCategoriesSum(
                                day,
                                categories.stream().map(category ->
                                        new Pair<>(
                                                category,
                                                operationSumGetterService
                                                        .getSumOperationsByAccountByCategoryByDate(account, category, year, month, day)
                                        )).collect(Collectors.toList())
                        )).toList();
    }

    public AllMonthData getAllMonthData(Account account, int year) {
        List<LocalDate> yearMonth = IntStream.range(1, 13)
                .mapToObj(monthNumber
                        -> LocalDate.now().withYear(year).withMonth(monthNumber))
                .toList();

        List<Double> incomes = yearMonth.stream()
                .map(localDate
                        -> operationSumGetterService
                        .getSumOperationsByAccountByCategoryTypeByDate(
                                account,
                                CategoryType.INCOME,
                                localDate.getYear(),
                                localDate.getMonthValue()))
                .collect(Collectors.toList());

        List<Double> expenses = yearMonth.stream()
                .map(localDate
                        -> operationSumGetterService
                        .getSumOperationsByAccountByCategoryTypeByDate(
                                account,
                                CategoryType.EXPENSE,
                                localDate.getYear(),
                                localDate.getMonthValue()))
                .collect(Collectors.toList());

        List<Double> balanceMonths = new ArrayList<>();
        balanceMonths.add(account.getBalance());
        for (int i = 0; i < expenses.size(); i++) {
            double current = balanceMonths.get(i);
            current -= incomes.get(i);
            current += expenses.get(i);
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

        List<DayCategoriesSum> incomes = getDayCategoriesSums(begin, end, incomesCategories, account, year, month);
        List<DayCategoriesSum> expenses = getDayCategoriesSums(begin, end, expensesCategories, account, year, month);

        AllMonthData allMonthData = getAllMonthData(account, year);

        return new AnalyticsResponse(incomes, expenses, allMonthData);
    }
}