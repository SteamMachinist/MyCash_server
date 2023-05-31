package tp35.mycashserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tp35.mycashserver.model.CategoryType;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String name;
    private CategoryType type;
    private Boolean isLimited;
    private Double spendingLimit;
    private Integer color;
}
