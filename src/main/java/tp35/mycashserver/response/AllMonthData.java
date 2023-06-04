package tp35.mycashserver.response;

import lombok.Data;

import java.util.List;

@Data
public class AllMonthData {
    private final Double accountLimit;
    private final Double target;
    private final List<Double> balanceMonths;
    private final List<Double> incomes;
    private final List<Double> expenses;
}
