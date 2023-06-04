package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import tp35.mycashserver.utils.Pair;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.AccountMapper;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Category;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.repository.OperationRepository;
import tp35.mycashserver.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationsGetterService {
    private final OperationRepository operationRepository;
    private final AccountMapper accountMapper;
    private final OperationMapper operationMapper;
    private final CategoryMapper categoryMapper;

    public List<Operation> getOperationsByAccount(Account account) {
        return operationMapper.toOperations(
                operationRepository.findAllByAccount(accountMapper.toAccountEntity(account)));
    }

    public List<Operation> getOperationsByAccountByDate(Account account, int year) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getDatesBorderYear(year, year);
        return getOperationsByAccountBetween(account, monthDates.getFirst(), monthDates.getSecond());
    }

    public List<Operation> getOperationsByAccountByCategoryByDate(Account account, Category category, int year) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getDatesBorderYear(year, year);
        return getOperationsByAccountByCategoryBetween(account, category, monthDates.getFirst(), monthDates.getSecond());
    }

    public List<Operation> getOperationsByAccountByCategoryTypeByDate(Account account, CategoryType categoryType, int year) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getDatesBorderYear(year, year);
        return getOperationsByAccountByCategoryTypeBetween(account, categoryType, monthDates.getFirst(), monthDates.getSecond());
    }

    public List<Operation> getOperationsByAccountByDate(Account account, int year, int month) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getDatesBorderMonth(year, month, year, month);
        return getOperationsByAccountBetween(account, monthDates.getFirst(), monthDates.getSecond());
    }

    public List<Operation> getOperationsByAccountByCategoryByDate(Account account, Category category, int year, int month) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getDatesBorderMonth(year, month, year, month);
        return getOperationsByAccountByCategoryBetween(account, category, monthDates.getFirst(), monthDates.getSecond());
    }

    public List<Operation> getOperationsByAccountByCategoryTypeByDate(Account account, CategoryType categoryType, int year, int month) {
        Pair<LocalDateTime, LocalDateTime> monthDates = DateUtils.getDatesBorderMonth(year, month, year, month);
        return getOperationsByAccountByCategoryTypeBetween(account, categoryType, monthDates.getFirst(), monthDates.getSecond());
    }

    public List<Operation> getOperationsByAccountByDate(Account account, int year, int month, int day) {
        Pair<LocalDateTime, LocalDateTime> dayDates = DateUtils.getDatesBorderMonthDays(year, month, day, year, month, day);
        return getOperationsByAccountBetween(account, dayDates.getFirst(), dayDates.getSecond());
    }

    public List<Operation> getOperationsByAccountByCategoryByDate(Account account, Category category, int year, int month, int day) {
        Pair<LocalDateTime, LocalDateTime> dayDates = DateUtils.getDatesBorderMonthDays(year, month, day, year, month, day);
        return getOperationsByAccountByCategoryBetween(account, category, dayDates.getFirst(), dayDates.getSecond());
    }

    public List<Operation> getOperationsByAccountByCategoryTypeByDate(Account account, CategoryType categoryType, int year, int month, int day) {
        Pair<LocalDateTime, LocalDateTime> dayDates = DateUtils.getDatesBorderMonthDays(year, month, day, year, month, day);
        return getOperationsByAccountByCategoryTypeBetween(account, categoryType, dayDates.getFirst(), dayDates.getSecond());
    }

    private List<Operation> getOperationsByAccountBetween(Account account,
                                                          LocalDateTime from, LocalDateTime to) {
        return operationMapper.toOperations(
                operationRepository.findAllByAccountAndDateTimeBetween(
                        accountMapper.toAccountEntity(account), from, to));
    }

    private List<Operation> getOperationsByAccountByCategoryBetween(Account account, Category category,
                                                                    LocalDateTime from, LocalDateTime to) {
        return operationMapper.toOperations(
                operationRepository.findAllByAccountAndCategoryAndDateTimeBetween(
                        accountMapper.toAccountEntity(account), categoryMapper.toCategoryEntity(category), from, to));
    }

    private List<Operation> getOperationsByAccountByCategoryTypeBetween(Account account, CategoryType categoryType,
                                                                    LocalDateTime from, LocalDateTime to) {
        return operationMapper.toOperations(
                operationRepository.findAllByAccountAndCategory_BaseCategory_TypeAndDateTimeBetween(
                        accountMapper.toAccountEntity(account), categoryType, from, to));
    }
}
