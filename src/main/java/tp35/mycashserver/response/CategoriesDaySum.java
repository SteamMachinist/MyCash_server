package tp35.mycashserver.response;

import lombok.Data;
import tp35.mycashserver.dto.CategoryDTO;
import tp35.mycashserver.utils.Pair;

import java.util.List;

@Data
public class CategoriesDaySum {
    private final Integer day;
    private final List<Pair<CategoryDTO, Double>> data;
}
