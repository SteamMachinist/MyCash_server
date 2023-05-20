package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.AccountToEntityMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountToEntityMapper accountToEntityMapper;

    public void add(Account account) {
        accountRepository.save(accountToEntityMapper.toAccountEntity(account));
    }
}
