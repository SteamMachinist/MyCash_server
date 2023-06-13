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
import tp35.mycashserver.model.User;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class OperationTest extends IntegrationEnvironment {
    @Autowired
    private UserService userService;
    @Autowired
    private OperationService operationService;

    @Test
    @Rollback
    @Transactional
    public void testOperationAdd() {
        //userService.addUser(new User(null, ));
    }
}
