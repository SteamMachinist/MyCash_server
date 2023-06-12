package tp35.mycashserver.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.dto.AccountDTO;
import tp35.mycashserver.mapper.AccountMapper;
import tp35.mycashserver.mapper.UserMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserMapper userMapper;

    public List<Account> getAllAccounts(User owner) {
        return accountMapper.toAccounts(accountRepository.findAllByOwner(userMapper.toUserEntity(owner)));
    }

    public Account getAccountById(Long id) {
        return accountMapper.toAccount(accountRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Account getAccountByOwnerAndName(User owner, String name) {
        return accountMapper.toAccount(
                accountRepository.findByOwnerAndName(userMapper.toUserEntity(owner), name));
    }

    public void addAccountForUser(Account account, User user) {
        account.setOwner(user);
        this.addAccount(account);
    }

    public void addAccount(Account account) {
        accountRepository.save(accountMapper.toAccountEntity(account));
    }

    public void updateAccount(User user, AccountDTO accountDTO, String accountName) {
        Account account = getAccountByOwnerAndName(user, accountName);
        account.setName(accountDTO.getName());
        account.setTarget(accountDTO.getTarget());
        account.setIsLimited(accountDTO.getIsLimited());
        account.setSpendingLimit(accountDTO.getSpendingLimit());
        this.addAccount(account);
    }

    public void deleteAccount(User user, String accountName) {
        accountRepository.delete(accountMapper.toAccountEntity(getAccountByOwnerAndName(user, accountName)));
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(accountMapper.toAccountEntity(account));
    }
}
