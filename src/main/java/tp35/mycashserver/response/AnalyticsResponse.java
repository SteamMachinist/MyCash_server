package tp35.mycashserver.response;

import lombok.Data;

import java.util.List;

@Data
public class AnalyticsResponse {
    private final List<DayCategoriesSum> incomes;
    private final List<DayCategoriesSum> expenses;
    private final AllMonthData all;
}
