package tp35.mycashserver.service;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tp35.mycashserver.IntegrationEnvironment;
import tp35.mycashserver.mapper.*;
import tp35.mycashserver.model.*;
import tp35.mycashserver.repository.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Rollback
public class OperationTest extends IntegrationEnvironment {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BaseCategoryRepository baseCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private OperationService operationService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BaseCategoryMapper baseCategoryMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private OperationMapper operationMapper;

    @Test
    @Transactional
    public void testAddGetOperation() {
        User user = new User(5L, "testname", "password123", Role.UNREGISTERED);
        userRepository.saveAndFlush(userMapper.toUserEntity(user));

        BaseCategory baseCategory = new BaseCategory(5L, "base category", CategoryType.INCOME, 123);
        baseCategoryRepository.saveAndFlush(baseCategoryMapper.toBaseCategoryEntity(baseCategory));

        Category category = new Category(5L, baseCategory, user, true, 5000.0, 123);
        categoryRepository.saveAndFlush(categoryMapper.toCategoryEntity(category));

        Account account = new Account(5L, user, "testaccount", 4000.0, 20000.0, true, 2000.0);
        accountRepository.saveAndFlush(accountMapper.toAccountEntity(account));

        Operation operation = new Operation(5L, account, category, 300.0, LocalDateTime.of(2023, 4, 15, 10, 45), "comment", null);
        operationRepository.saveAndFlush(operationMapper.toOperationEntity(operation));

        Operation testedOperation = operationService.getOperationById(5L);

        assertThat(testedOperation.getValue(), is(operation.getValue()));
        assertThat(testedOperation.getDateTime(), is(operation.getDateTime()));
        assertThat(testedOperation.getAccount().getName(), is(account.getName()));
        assertThat(testedOperation.getCategory().getBaseCategory().getName(), is(baseCategory.getName()));
    }
}
