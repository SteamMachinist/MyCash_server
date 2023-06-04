package tp35.mycashserver.response;

import lombok.Data;

import tp35.mycashserver.model.Category;
import tp35.mycashserver.utils.Pair;

import java.util.List;

@Data
public class DayCategoriesSum {
    private final Integer day;
    private final List<Pair<Category, Double>> data;
}
