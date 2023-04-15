package tp35.mycashserver.model;

import lombok.Value;

import java.awt.*;

@Value
public class Category {
    BaseCategory baseCategory;
    User user;
    Boolean isLimited;
    Double limit;
    Color color;
}
