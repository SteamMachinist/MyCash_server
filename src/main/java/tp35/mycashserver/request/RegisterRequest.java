package tp35.mycashserver.request;

import lombok.Data;

@Data
public class RegisterRequest {
    String newUsername;
    String password;
}
