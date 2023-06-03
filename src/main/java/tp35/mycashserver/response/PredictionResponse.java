package tp35.mycashserver.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import tp35.mycashserver.dto.CategoryDTO;

@Data
public class PredictionResponse {
    private final Double expensePrediction;
    private final Double incomePrediction;
    private final CategoryDTO topCategory;
    private final Double topCategoryPrediction;
}
