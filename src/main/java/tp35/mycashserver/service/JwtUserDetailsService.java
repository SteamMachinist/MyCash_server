package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.UserToEntityMapper;
import tp35.mycashserver.model.JwtUserDetails;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserToEntityMapper userToEntityMapper;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = userToEntityMapper.toUser(userRepository.findByUsername(username));
        return new JwtUserDetails(user.getId(), username, user.getPassword(), user.getRoles());
    }

}
