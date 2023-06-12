package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tp35.mycashserver.model.JwtUserDetails;
import tp35.mycashserver.model.User;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = userService.getUserByUsername(username);
        return new JwtUserDetails(user.getId(), username, user.getPassword(), Collections.singleton(user.getRole()));
    }
}
