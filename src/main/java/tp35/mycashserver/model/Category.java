package tp35.mycashserver.model;

import lombok.Value;
import tp35.mycashserver.entity.BaseCategoryEntity;
import tp35.mycashserver.entity.UserEntity;

import java.awt.*;

@Value
public class Category {
    Long id;
    BaseCategoryEntity baseCategory;
    UserEntity user;
    Boolean isLimited;
    Double spendingLimit;
    Color color;
}
