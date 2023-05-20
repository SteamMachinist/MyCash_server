package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tp35.mycashserver.entity.UserEntity;
import tp35.mycashserver.mapper.UserToEntityMapper;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserToEntityMapper userMapper;

    private final PasswordEncoder encoder;

    public User getById(Long id) {
        return userRepository.findById(id).map(userMapper::userEntityToUser).orElse(new User(null, null, null));
    }

    public User getByUsername(String username) {
        return userMapper.userEntityToUser(userRepository.findByUsername(username));
    }

    public void add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(new UserEntity(user.getId(), user.getUsername(), user.getPassword()));
    }
}
