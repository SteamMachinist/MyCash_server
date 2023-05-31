package tp35.mycashserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    private Long id;
    private Account account;
    private Category category;
    private Double value;
    private LocalDateTime dateTime;
    private String comment;
}
