package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.User;
import tp35.mycashserver.response.MainScreenAccountResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainScreenService {
    private final AccountService accountService;
    private final OperationsGetterService operationsGetterService;
    private final OperationMapper operationMapper;

    public List<MainScreenAccountResponse> getDataForMainScreen(User user, int year, int month, int day) {
        List<Account> accounts = accountService.getAllAccounts(user);
        return accounts.stream()
                .map(account -> new MainScreenAccountResponse(
                        account.getName(),
                        account.getBalance(),
                        operationMapper.toOperationDTOs(
                                operationsGetterService.getOperationsByAccountByDate(account, year, month, day))))
                .collect(Collectors.toList());
    }

    public List<MainScreenAccountResponse> getDataForMainScreen(User user, int year, int month) {
        List<Account> accounts = accountService.getAllAccounts(user);
        return accounts.stream()
                .map(account -> new MainScreenAccountResponse(
                        account.getName(),
                        account.getBalance(),
                        operationMapper.toOperationDTOs(
                                operationsGetterService.getOperationsByAccountByDate(account, year, month))))
                .collect(Collectors.toList());
    }

}
