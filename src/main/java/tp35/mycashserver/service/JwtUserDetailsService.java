package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.UserToEntityMapper;
import tp35.mycashserver.model.JwtUserDetails;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;

    private final UserRepository userRepository;
    private final UserToEntityMapper userToEntityMapper;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = userToEntityMapper.userEntityToUser(userRepository.findByUsername(username));
        return new JwtUserDetails(user.getId(), username, Integer.toString(user.hashCode()), Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
    }

}
