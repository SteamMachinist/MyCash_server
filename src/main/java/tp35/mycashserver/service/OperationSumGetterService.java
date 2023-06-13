package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.AccountMapper;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.repository.OperationRepository;
import tp35.mycashserver.utils.DateUtils;
import tp35.mycashserver.utils.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationSumGetterService {
    private final OperationRepository operationRepository;
    private final AccountMapper accountMapper;
    private final CategoryMapper categoryMapper;

    public double getOperationSumBy(Account account, int year, int month) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getBorderingDatesBetween(year, month, year, month);
        return operationRepository.sumOfOperationsBy(accountMapper.toAccountEntity(account), monthDates.getFirst(), monthDates.getSecond())
                .orElse(0.0);
    }

    public double getOperationSumBy(Account account, int year, int month, int day) {
        Pair<LocalDateTime, LocalDateTime> dayDates = DateUtils.getBorderingDatesBetween(year, month, day, year, month, day);
        return operationRepository.sumOfOperationsBy(accountMapper.toAccountEntity(account), dayDates.getFirst(), dayDates.getSecond())
                .orElse(0.0);
    }

    public double getOperationSumBy(Account account, Category category, int year, int month) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getBorderingDatesBetween(year, month, year, month);
        return operationRepository.sumOfOperationsBy(accountMapper.toAccountEntity(account),
                categoryMapper.toCategoryEntity(category), monthDates.getFirst(), monthDates.getSecond())
                .orElse(0.0);
    }

    public double getOperationSumBy(Account account, Category category, int year, int month, int day) {
        Pair<LocalDateTime, LocalDateTime> dayDates = DateUtils.getBorderingDatesBetween(year, month, day, year, month, day);
        return operationRepository.sumOfOperationsBy(accountMapper.toAccountEntity(account),
                categoryMapper.toCategoryEntity(category), dayDates.getFirst(), dayDates.getSecond())
                .orElse(0.0);
    }

    public double getOperationSumBy(Account account, CategoryType categoryType, int year, int month) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getBorderingDatesBetween(year, month, year, month);
        return operationRepository.sumOfOperationsBy(accountMapper.toAccountEntity(account),
                categoryType, monthDates.getFirst(), monthDates.getSecond())
                .orElse(0.0);
    }

    public double getOperationSumBy(Account account, CategoryType categoryType, int year, int month, int day) {
        Pair<LocalDateTime, LocalDateTime> dayDates = DateUtils.getBorderingDatesBetween(year, month, day, year, month, day);
        return operationRepository.sumOfOperationsBy(accountMapper.toAccountEntity(account),
                categoryType, dayDates.getFirst(), dayDates.getSecond())
                .orElse(0.0);
    }

    public List<Double> getSumByTypeByMonthList(List<LocalDate> months, Account account, CategoryType categoryType) {
        return months.stream()
                .map(localDate ->
                        getOperationSumBy(
                                account,
                                categoryType,
                                localDate.getYear(),
                                localDate.getMonthValue()))
                .toList();
    }
}
