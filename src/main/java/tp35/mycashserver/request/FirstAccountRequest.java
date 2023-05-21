package tp35.mycashserver.request;

import lombok.Data;

@Data
public class FirstAccountRequest {
    private final String accountName;
    private final Double accountBalance;
}
