package tp35.mycashserver.model;

import lombok.Value;

@Value
public class Account {
    Long id;
    User owner;
    String name;
    Double balance;
    Double target;
    Boolean isLimited;
    Double spendingLimit;
}
