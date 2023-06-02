package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp35.mycashserver.model.User;
import tp35.mycashserver.request.AuthenticationRequest;
import tp35.mycashserver.request.FirstAccountRequest;
import tp35.mycashserver.request.RegisterRequest;
import tp35.mycashserver.response.TokenResponse;
import tp35.mycashserver.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/new")
    public TokenResponse newUserWithAccount(@RequestBody FirstAccountRequest firstAccountRequest) {
        User user = authenticationService.createNewUserFromRequest(firstAccountRequest);
        return authenticationService.getUserToken(user);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = authenticationService.loginUser(authenticationRequest);
        return authenticationService.getUserToken(user);
    }

    @PostMapping("/register")
    public TokenResponse register(@RequestBody RegisterRequest registerRequest) {
        User user = authenticationService.registerUser(registerRequest);
        return authenticationService.getUserToken(user);
    }
}