package tp35.mycashserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tp35.mycashserver.dto.AccountDTO;
import tp35.mycashserver.mapper.AccountMapper;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.AuthenticationService;

import java.util.List;

@Tag(name = "Account", description = "Account controller")
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Operation(summary = "Get all user accounts")
    @GetMapping("/get_all")
    public List<AccountDTO> getUserAccounts() {
        User user = authenticationService.getAuthenticatedUser();
        return accountMapper.toAccountDTOs(accountService.getAllAccounts(user));
    }

    @Operation(summary = "Get user account by account name")
    @GetMapping("/get/{accountName}")
    public AccountDTO getUserAccount(@PathVariable String accountName) {
        User user = authenticationService.getAuthenticatedUser();
        return accountMapper.toAccountDTO(accountService.getAccountByOwnerAndName(user, accountName));
    }

    @Operation(summary = "Add account for user")
    @PostMapping("/add")
    public void addUserAccount(@RequestBody AccountDTO accountDTO) {
        try {
            User user = authenticationService.getAuthenticatedUser();
            accountService.addAccountForUser(accountMapper.toAccount(accountDTO), user);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already has an account with that name");
        }
    }

    @Operation(summary = "Update user account")
    @PostMapping("/update/{accountName}")
    public void updateUserAccount(@RequestBody AccountDTO accountDTO, @PathVariable String accountName) {
        try {
            User user = authenticationService.getAuthenticatedUser();
            accountService.updateAccount(user, accountDTO, accountName);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already has an account with that name");
        }
    }

    @Operation(summary = "Delete user account")
    @DeleteMapping("/delete/{accountName}")
    public void deleteUserAccount(@PathVariable String accountName) {
        User user = authenticationService.getAuthenticatedUser();
        accountService.deleteAccount(user, accountName);
    }
}
