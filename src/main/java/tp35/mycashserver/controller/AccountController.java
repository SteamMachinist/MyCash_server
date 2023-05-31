package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tp35.mycashserver.dto.AccountDTO;
import tp35.mycashserver.mapper.AccountMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping("/get")
    public List<AccountDTO> getUserAccounts() {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return accountService.getAllAccounts(user).stream().map(accountMapper::toAccountDTO).collect(Collectors.toList());
    }

    @GetMapping("/get/{accountName}")
    public AccountDTO getUserAccount(@PathVariable String accountName) {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return accountMapper.toAccountDTO(accountService.getAccountByOwnerAndName(user, accountName));
    }

    @PostMapping("/add")
    public void addUserAccount(@RequestBody AccountDTO accountDTO) {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Account account = accountMapper.toAccount(accountDTO);
        account.setOwner(user);
        accountService.addAccount(account);
    }


    @PostMapping("/update")
    public void updateUserAccount(@RequestBody AccountDTO accountDTO) {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Account oldAccount = accountService.getAccountByOwnerAndName(user, accountDTO.getName());
    }


    @DeleteMapping("/delete/{accountName}")
    public void deleteUserAccount(@PathVariable String accountName) {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        accountService.deleteAccount(accountService.getAccountByOwnerAndName(user, accountName));
    }
}
