package tp35.mycashserver.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.UserMapper;
import tp35.mycashserver.model.Role;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    public Map<Role, Integer> getUsersNumber() {
        Map<Role, Integer> usersNumber = new HashMap<>();
        usersNumber.put(Role.UNREGISTERED, userRepository.countByRole(Role.UNREGISTERED));
        usersNumber.put(Role.REGISTERED, userRepository.countByRole(Role.REGISTERED));
        usersNumber.put(Role.ADMIN, userRepository.countByRole(Role.ADMIN));
        return usersNumber;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toUser).orElseThrow(EntityNotFoundException::new);
    }

    public User getUserByUsername(String username) {
        return userMapper.toUser(userRepository.findByUsername(username));
    }

    public void addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(userMapper.toUserEntity(user));
    }

    public void deleteUser(User user) {
        userRepository.delete(userMapper.toUserEntity(user));
    }
}
