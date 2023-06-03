package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tp35.mycashserver.dto.AccountDTO;
import tp35.mycashserver.mapper.AccountMapper;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.AuthenticationService;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping("/get_all")
    public List<AccountDTO> getUserAccounts() {
        User user = authenticationService.getAuthenticatedUser();
        return accountMapper.toAccountDTOs(accountService.getAllAccounts(user));
    }

    @GetMapping("/get/{accountName}")
    public AccountDTO getUserAccount(@PathVariable String accountName) {
        User user = authenticationService.getAuthenticatedUser();
        return accountMapper.toAccountDTO(accountService.getAccountByOwnerAndName(user, accountName));
    }

    @PostMapping("/add")
    public void addUserAccount(@RequestBody AccountDTO accountDTO) {
        User user = authenticationService.getAuthenticatedUser();
        accountService.addAccountForUser(accountMapper.toAccount(accountDTO), user);
    }

    @PostMapping("/update")
    public void updateUserAccount(@RequestBody AccountDTO accountDTO) {
        User user = authenticationService.getAuthenticatedUser();
        accountService.updateAccount(user, accountDTO);
    }

    @DeleteMapping("/delete/{accountName}")
    public void deleteUserAccount(@PathVariable String accountName) {
        User user = authenticationService.getAuthenticatedUser();
        accountService.deleteAccount(user, accountName);
    }
}
