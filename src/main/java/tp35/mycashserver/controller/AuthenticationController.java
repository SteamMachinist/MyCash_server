package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.User;
import tp35.mycashserver.request.AuthenticationRequest;
import tp35.mycashserver.request.FirstAccountRequest;
import tp35.mycashserver.request.RegisterRequest;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.JwtTokenService;
import tp35.mycashserver.service.JwtUserDetailsService;
import tp35.mycashserver.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AccountService accountService;
    private final JwtTokenService jwtTokenService;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/new")
    public String newUserWithAccount(@RequestBody FirstAccountRequest firstAccountRequest) {
        User user = new User(null, UUID.randomUUID().toString(), "");
        userService.add(user);
        Account account = new Account(null, userService.getByUsername(user.getUsername()), firstAccountRequest.getName(), firstAccountRequest.getBalance(), null, false, null);
        accountService.add(account);
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        String oldUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.getByUsername(oldUsername);
        user.setUsername(registerRequest.getNewUsername());
        user.setPassword(registerRequest.getPassword());
        userService.add(user);

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }
}
