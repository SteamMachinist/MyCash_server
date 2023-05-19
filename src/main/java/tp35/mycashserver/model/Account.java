package tp35.mycashserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    Long id;
    User owner;
    String name;
    Double balance;
    Double target;
    Boolean isLimited;
    Double spendingLimit;
}
