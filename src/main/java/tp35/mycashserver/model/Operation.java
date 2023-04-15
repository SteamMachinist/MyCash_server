package tp35.mycashserver.model;

import lombok.Value;

@Value
public class Operation {
    Account account;
    Category category;
    Double value;
}
