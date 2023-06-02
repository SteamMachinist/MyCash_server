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
import tp35.mycashserver.service.AccountOperationsGetterService;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.AuthenticationService;

import java.util.List;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainScreenController {
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final AccountOperationsGetterService accountOperationsGetterService;
    private final OperationMapper operationMapper;

    @GetMapping("/get/{accountName}")
    public List<OperationDTO> getOperationsFor(@PathVariable String accountName) {
        User user = authenticationService.getAuthenticatedUser();
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        return operationMapper.toOperationDTOs(accountOperationsGetterService.getOperationsByAccount(account));
    }

    @GetMapping("/get/{accountName}/{year}/{month}")
    public List<OperationDTO> getOperationsFor(@PathVariable String accountName, @PathVariable int year, @PathVariable int month) {
        User user = authenticationService.getAuthenticatedUser();
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        return operationMapper.toOperationDTOs(accountOperationsGetterService.getOperationsByAccountAndDate(account, year, month));
    }

    @GetMapping("/get/{accountName}/{year}/{month}/{day}")
    public List<OperationDTO> getOperationsFor(@PathVariable String accountName, @PathVariable int year, @PathVariable int month, @PathVariable int day) {
        User user = authenticationService.getAuthenticatedUser();
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        return operationMapper.toOperationDTOs(accountOperationsGetterService.getOperationsByAccountAndDate(account, year, month, day));
    }
}
