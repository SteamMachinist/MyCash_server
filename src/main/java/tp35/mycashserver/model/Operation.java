package tp35.mycashserver.model;

import lombok.Value;

@Value
public class Operation {
    Long id;
    Account account;
    Category category;
    Double value;
}
