package tp35.mycashserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCategory {
    private Long id;
    private String name;
    private CategoryType type;
    private Integer color;
}
