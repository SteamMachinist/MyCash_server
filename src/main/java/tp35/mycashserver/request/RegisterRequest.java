package tp35.mycashserver.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private final String username;
    private final String password;
}
