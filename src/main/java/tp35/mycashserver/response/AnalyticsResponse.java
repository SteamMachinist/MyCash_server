package tp35.mycashserver.response;

import lombok.Data;

import java.util.List;

@Data
public class AnalyticsResponse {
    private final List<CategoriesDaySum> incomes;
    private final List<CategoriesDaySum> expenses;
    private final AllMonthData all;
}
