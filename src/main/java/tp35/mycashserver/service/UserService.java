package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
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

    public User getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::userEntityToUser).orElse(new User(null, null, null));
    }

    public User getUserByToken(String token) {
        return userMapper.userEntityToUser(userRepository.findByToken(token));
    }

    public void addUser(User user) {
        //userRepository.save(userMapper.userToUserEntity(user));
        userRepository.save(new UserEntity(user.getId(), user.getName(), user.getToken()));
    }
}
