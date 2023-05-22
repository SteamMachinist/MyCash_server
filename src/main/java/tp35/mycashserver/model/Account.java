package tp35.mycashserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private User owner;
    private String name;
    private Double balance;
    private Double target;
    private Boolean isLimited;
    private Double spendingLimit;
}
