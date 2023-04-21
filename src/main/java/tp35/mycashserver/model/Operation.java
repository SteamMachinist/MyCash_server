package tp35.mycashserver.model;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Operation {
    Long id;
    Account account;
    Category category;
    Double value;
    LocalDateTime dateTime;
}
