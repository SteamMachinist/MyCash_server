package tp35.mycashserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id;
    private BaseCategory baseCategory;
    private User user;
    private Boolean isLimited;
    private Double spendingLimit;
    private Integer color;
}
