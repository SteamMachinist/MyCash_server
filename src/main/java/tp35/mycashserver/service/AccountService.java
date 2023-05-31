package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.AccountMapper;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.mapper.UserMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.AccountRepository;
import tp35.mycashserver.repository.OperationRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;
    private final AccountMapper accountMapper;
    private final UserMapper userMapper;
    private final OperationMapper operationMapper;

    public void add(Account account) {
        accountRepository.save(accountMapper.toAccountEntity(account));
    }

    public Account getAccountByOwnerAndName(User owner, String name) {
        return accountMapper.toAccount(
                accountRepository.findByOwnerAndName(userMapper.toUserEntity(owner), name));
    }

    public List<Operation> getOperationsOfAccount(Account account) {
        return operationMapper.toOperations(
                operationRepository.findAllByAccount(accountMapper.toAccountEntity(account)));
    }

    public List<Operation> getOperationsOfAccountAndDate(Account account, int year, int month) {
        LocalDateTime base = LocalDateTime.now();
        LocalDateTime from = base.toLocalDate()
                .withYear(year)
                .withMonth(month)
                .with(TemporalAdjusters.firstDayOfMonth())
                .atTime(LocalTime.MIN);

        LocalDateTime to = base.toLocalDate()
                .withYear(year)
                .withMonth(month)
                .with(TemporalAdjusters.lastDayOfMonth())
                .atTime(LocalTime.MAX).minus(1, ChronoUnit.SECONDS);

        return getOperationsOfAccountBetween(account, from, to);
    }

    public List<Operation> getOperationsOfAccountAndDate(Account account, int year, int month, int day) {
        LocalDateTime base = LocalDateTime.now();
        LocalDateTime from = base.toLocalDate()
                .withYear(year)
                .withMonth(month)
                .withDayOfMonth(day)
                .atTime(LocalTime.MIN);

        LocalDateTime to = base.toLocalDate()
                .withYear(year)
                .withMonth(month)
                .withDayOfMonth(day)
                .atTime(LocalTime.MAX).minus(1, ChronoUnit.SECONDS);

        return getOperationsOfAccountBetween(account, from, to);
    }

    private List<Operation> getOperationsOfAccountBetween(Account account, LocalDateTime from, LocalDateTime to) {
        return operationMapper.toOperations(
                operationRepository.findAllByAccountAndCreatedBetween(
                        accountMapper.toAccountEntity(account), from, to));
    }
}
