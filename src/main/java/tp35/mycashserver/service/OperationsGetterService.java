package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.AccountMapper;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.repository.OperationRepository;
import tp35.mycashserver.utils.DateUtils;
import tp35.mycashserver.utils.Pair;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationsGetterService {
    private final OperationRepository operationRepository;
    private final AccountMapper accountMapper;
    private final OperationMapper operationMapper;

    public List<Operation> getOperationsByAccountByDate(Account account, int year, int month) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getBorderingDatesBetween(year, month, year, month);
        return getOperationsByAccountBetween(account, monthDates.getFirst(), monthDates.getSecond());
    }

    public List<Operation> getOperationsByAccountByDate(Account account, int year, int month, int day) {
        Pair<LocalDateTime, LocalDateTime> dayDates = DateUtils.getBorderingDatesBetween(year, month, day, year, month, day);
        return getOperationsByAccountBetween(account, dayDates.getFirst(), dayDates.getSecond());
    }

    private List<Operation> getOperationsByAccountBetween(Account account,
                                                          LocalDateTime from, LocalDateTime to) {
        return operationMapper.toOperations(
                operationRepository.findAllByAccountAndDateTimeBetween(
                        accountMapper.toAccountEntity(account), from, to));
    }
}
