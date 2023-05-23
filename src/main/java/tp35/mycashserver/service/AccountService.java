package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.AccountToEntityMapper;
import tp35.mycashserver.mapper.OperationToEntityMapper;
import tp35.mycashserver.mapper.UserToEntityMapper;
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
    private final AccountToEntityMapper accountToEntityMapper;
    private final UserToEntityMapper userToEntityMapper;
    private final OperationToEntityMapper operationToEntityMapper;

    public void add(Account account) {
        accountRepository.save(accountToEntityMapper.toAccountEntity(account));
    }

    public Account getAccountByOwnerAndName(User owner, String name) {
        return accountToEntityMapper.toAccount(
                accountRepository.findByOwnerAndName(userToEntityMapper.toUserEntity(owner), name));
    }

    public List<Operation> getOperationsOfAccount(Account account) {
        return operationToEntityMapper.toOperationList(
                operationRepository.findAllByAccount(accountToEntityMapper.toAccountEntity(account)));
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
        return operationToEntityMapper.toOperationList(
                operationRepository.findAllByAccountAndCreatedBetween(
                        accountToEntityMapper.toAccountEntity(account), from, to));
    }
}
