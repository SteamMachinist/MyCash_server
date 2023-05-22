package tp35.mycashserver.model;

import lombok.Value;
import tp35.mycashserver.entity.BaseCategoryEntity;
import tp35.mycashserver.entity.UserEntity;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id;
    private BaseCategoryEntity baseCategory;
    private UserEntity user;
    private Boolean isLimited;
    private Double spendingLimit;
    private Color color;
}
