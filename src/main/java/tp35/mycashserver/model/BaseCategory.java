package tp35.mycashserver.model;

import lombok.Value;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCategory {
    private Long id;
    private String name;
    private CategoryType type;
    private Color color;
}
