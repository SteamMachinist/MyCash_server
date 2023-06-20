package tp35.mycashserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tp35.mycashserver.model.User;
import tp35.mycashserver.request.AuthenticationRequest;
import tp35.mycashserver.request.FirstAccountRequest;
import tp35.mycashserver.request.RegisterRequest;
import tp35.mycashserver.response.TokenResponse;
import tp35.mycashserver.service.AuthenticationService;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Create new user with account")
    @PostMapping("/new")
    public TokenResponse newUserWithAccount(@RequestBody FirstAccountRequest firstAccountRequest) {
        User user = authenticationService.createNewUserFromRequest(firstAccountRequest);
        return authenticationService.getUserToken(user);
    }

    @Operation(summary = "Login to existing registered user")
    @PostMapping("/login")
    public TokenResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            User user = authenticationService.loginUser(authenticationRequest);
            return authenticationService.getUserToken(user);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username of password");
        }
    }

    @Operation(summary = "Register unregistered user")
    @PostMapping("/register")
    public TokenResponse register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = authenticationService.registerUser(registerRequest);
            return authenticationService.getUserToken(user);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already in use");
        }
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("/delete")
    public void delete() {
        authenticationService.deleteUser();
    }
}