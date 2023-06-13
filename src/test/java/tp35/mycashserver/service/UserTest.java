package tp35.mycashserver.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tp35.mycashserver.IntegrationEnvironment;
import tp35.mycashserver.mapper.UserMapper;
import tp35.mycashserver.model.Role;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.UserRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Rollback
public class UserTest extends IntegrationEnvironment {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional
    public void testSaveUserGetUser() {
        User user = new User(5L, "testname", "password123", Role.UNREGISTERED);
        userRepository.saveAndFlush(userMapper.toUserEntity(user));
        User testedUser = userService.getUserByUsername("testname");

        assertThat(testedUser.getUsername(), is(user.getUsername()));
        assertThat(testedUser.getPassword(), is(notNullValue()));
        assertThat(testedUser.getRole(), is(Role.UNREGISTERED));
    }
}
