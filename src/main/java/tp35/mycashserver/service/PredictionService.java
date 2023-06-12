package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.model.User;
import tp35.mycashserver.response.PredictionResponse;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PredictionService {
    private final OperationSumGetterService operationSumGetterService;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Value("${prediction.month_before_calculated}")
    private int monthBeforeCalculated;

    public PredictionResponse getPredictionFor(User user, Account account, int year, int month) throws Exception {

        List<Category> expensesCategories = categoryService.getUserCategoriesByType(user, CategoryType.EXPENSE);

        List<LocalDate> months = new ArrayList<>(IntStream.range(1, monthBeforeCalculated + 1)
                .mapToObj(value -> LocalDate.now().withYear(year).withMonth(month).minusMonths(value)).toList());
        Collections.reverse(months);

        List<Double> incomesPerMonths = operationSumGetterService.getSumByTypeByMonthList(months, account, CategoryType.INCOME);
        List<Double> expensesPerMonths = operationSumGetterService.getSumByTypeByMonthList(months, account, CategoryType.EXPENSE);

        Map<Category, List<Double>> categoryExpensesPerMonths = expensesCategories.stream()
                .collect(
                        Collectors.toMap(
                                category -> category,
                                category -> months.stream()
                                        .map(localDate ->
                                                operationSumGetterService.getSumOperationsByAccountByCategoryByDate(
                                                        account,
                                                        category,
                                                        localDate.getYear(),
                                                        localDate.getMonthValue()))
                                        .toList()));

        Map.Entry<Category, List<Double>> maxCategory = categoryExpensesPerMonths.entrySet()
                .stream()
                .max(Comparator.comparing(e -> {
                    try {
                        return e.getValue().stream().reduce(Double::sum).orElseThrow(Exception::new);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                })).orElseThrow(Exception::new);

        double[] numbers = IntStream.range(0, monthBeforeCalculated).mapToDouble(i -> i).toArray();

        PolynomialFunction expensesFunction = getExtrapolationFunctionFor(numbers, expensesPerMonths.stream().mapToDouble(i -> i).toArray());
        PolynomialFunction incomeFunction = getExtrapolationFunctionFor(numbers, incomesPerMonths.stream().mapToDouble(i -> i).toArray());
        PolynomialFunction topCategoryFunction = getExtrapolationFunctionFor(numbers, maxCategory.getValue().stream().mapToDouble(i -> i).toArray());

        return new PredictionResponse(
                expensesFunction.value(monthBeforeCalculated),
                incomeFunction.value(monthBeforeCalculated),
                categoryMapper.toCategoryDTO(maxCategory.getKey()),
                topCategoryFunction.value(monthBeforeCalculated));
    }

    private PolynomialFunction getExtrapolationFunctionFor(double[] xs, double[] ys) {
        PolynomialSplineFunction function = new SplineInterpolator().interpolate(xs, ys);
        PolynomialFunction[] splines = function.getPolynomials();
        return splines[splines.length - 1];
    }
}
