package tp35.mycashserver.model;

import lombok.Value;

import java.awt.*;

@Value
public class BaseCategory {
    Long id;
    String name;
    CategoryType type;
    Color color;
}
