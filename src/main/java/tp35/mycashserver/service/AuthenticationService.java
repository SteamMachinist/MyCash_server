package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Role;
import tp35.mycashserver.model.User;
import tp35.mycashserver.request.AuthenticationRequest;
import tp35.mycashserver.request.FirstAccountRequest;
import tp35.mycashserver.request.RegisterRequest;
import tp35.mycashserver.response.TokenResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final AccountService accountService;
    private final JwtTokenService jwtTokenService;

    public User getAuthenticatedUser() {
        return userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public User loginUser(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return getAuthenticatedUser();
    }

    public User createNewUserFromRequest(FirstAccountRequest firstAccountRequest) {
        User user = new User(null, UUID.randomUUID().toString(), "", Role.UNREGISTERED);
        userService.addUser(user);
        Account account = new Account(
                null,
                userService.getUserByUsername(user.getUsername()),
                firstAccountRequest.getAccountName(),
                firstAccountRequest.getAccountBalance(),
                null, false, null);
        accountService.addAccount(account);
        categoryService.addUserCategoriesFormBaseCategories(userService.getUserByUsername(user.getUsername()));
        return user;
    }

    public TokenResponse getUserToken(User user) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return new TokenResponse(jwtTokenService.generateToken(userDetails));
    }

    public User registerUser(RegisterRequest registerRequest) {
        User user = getAuthenticatedUser();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setRole(Role.REGISTERED);
        userService.addUser(user);
        return user;
    }

    public void deleteUser() {
        User user = getAuthenticatedUser();
        userService.deleteUser(user);
    }
}
