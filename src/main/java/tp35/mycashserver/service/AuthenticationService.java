package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tp35.mycashserver.mapper.UserMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.JwtUserDetails;
import tp35.mycashserver.model.Role;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.UserRepository;
import tp35.mycashserver.request.AuthenticationRequest;
import tp35.mycashserver.request.FirstAccountRequest;
import tp35.mycashserver.response.TokenResponse;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    //private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AccountService accountService;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = userService.getUserByUsername(username);
        return new JwtUserDetails(user.getId(), username, user.getPassword(), user.getRoles());
    }

    public User getAuthenticatedUser() {
        return userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

//    public User loginUser(AuthenticationRequest authenticationRequest) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//        } catch (final BadCredentialsException ex) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }
//        return getAuthenticatedUser();
//    }

    public User createNewUserFromRequest(FirstAccountRequest firstAccountRequest) {
        User user = new User(null, UUID.randomUUID().toString(), "", Collections.singleton(Role.UNREGISTERED));
        userService.addUser(user);
        Account account = new Account(null, userService.getUserByUsername(
                user.getUsername()),
                firstAccountRequest.getAccountName(),
                firstAccountRequest.getAccountBalance(),
                null, false, null);
        accountService.addAccount(account);
        return user;
    }

    public TokenResponse getUserToken(User user) {
        final UserDetails userDetails = loadUserByUsername(user.getUsername());
        return new TokenResponse(jwtTokenService.generateToken(userDetails));
    }
}
