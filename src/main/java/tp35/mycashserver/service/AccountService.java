package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.AccountToEntityMapper;
import tp35.mycashserver.mapper.UserToEntityMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.repository.AccountRepository;
import tp35.mycashserver.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountToEntityMapper accountToEntityMapper;
    private final UserToEntityMapper userToEntityMapper;
    private final UserRepository userRepository;

    public void add(Account account) {
//        AccountEntity accountEntity = new AccountEntity();
//        accountEntity.setId(account.getId());
//        accountEntity.setName(account.getName());
//        UserEntity userEntity = userToEntityMapper.userToUserEntity(account.getOwner());
//        userRepository.save(userEntity);
//        accountEntity.setOwner(userEntity);
//        accountEntity.setIsLimited(account.getIsLimited());
//        accountEntity.setSpendingLimit(account.getSpendingLimit());
//        accountEntity.setTarget(account.getTarget());
//        accountRepository.save(accountEntity);
        accountRepository.save(accountToEntityMapper.toAccountEntity(account));
    }
}
