package tp35.mycashserver.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private final String login;
    private final String password;
}
