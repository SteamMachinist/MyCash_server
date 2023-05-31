package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.AccountMapper;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.repository.OperationRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountOperationsGetterService {
    private final OperationRepository operationRepository;
    private final AccountMapper accountMapper;
    private final OperationMapper operationMapper;

    public List<Operation> getOperationsByAccount(Account account) {
        return operationMapper.toOperations(
                operationRepository.findAllByAccount(accountMapper.toAccountEntity(account)));
    }

    public List<Operation> getOperationsByAccountAndDate(Account account, int year, int month) {
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

        return getOperationsByAccountBetween(account, from, to);
    }

    public List<Operation> getOperationsByAccountAndDate(Account account, int year, int month, int day) {
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

        return getOperationsByAccountBetween(account, from, to);
    }

    private List<Operation> getOperationsByAccountBetween(Account account, LocalDateTime from, LocalDateTime to) {
        return operationMapper.toOperations(
                operationRepository.findAllByAccountAndDateTimeBetween(
                        accountMapper.toAccountEntity(account), from, to));
    }
}
