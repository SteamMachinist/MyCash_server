package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.UserMapper;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    public User getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toUser).orElse(new User(null, null, null, null));
    }

    public User getUserByUsername(String username) {
        return userMapper.toUser(userRepository.findByUsername(username));
    }

    public void addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(userMapper.toUserEntity(user));
    }
}
