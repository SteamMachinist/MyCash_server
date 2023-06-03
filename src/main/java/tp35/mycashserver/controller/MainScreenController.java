package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.AuthenticationService;
import tp35.mycashserver.service.OperationsGetterService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainScreenController {
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final OperationsGetterService operationsGetterService;
    private final OperationMapper operationMapper;

    @GetMapping("/get/{year}/{month}")
    public Map<String, List<OperationDTO>> getAccountsOperationsMapFor(@PathVariable int year, @PathVariable int month) {
        User user = authenticationService.getAuthenticatedUser();
        List<Account> accounts = accountService.getAllAccounts(user);
        return accounts.stream().collect(Collectors.toMap(
                account -> account.getName() + ":" + account.getBalance(),
                account -> operationMapper.toOperationDTOs(
                        operationsGetterService.getOperationsByAccountByDate(account, year, month))));
    }

    @GetMapping("/get/{year}/{month}/{day}")
    public Map<String, List<OperationDTO>> getAccountsOperationsMapFor(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        User user = authenticationService.getAuthenticatedUser();
        List<Account> accounts = accountService.getAllAccounts(user);
        return accounts.stream().collect(Collectors.toMap(
                account -> account.getName() + ":" + account.getBalance(),
                account -> operationMapper.toOperationDTOs(
                        operationsGetterService.getOperationsByAccountByDate(account, year, month, day))));
    }
}
