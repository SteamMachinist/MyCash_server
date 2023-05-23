package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainScreenController {
    private final UserService userService;
    private final AccountService accountService;

    @GetMapping("/get/{accountName}")
    public List<Operation> getOperationsFor(@PathVariable String accountName) {
        User user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        return accountService.getOperationsOfAccount(account);
    }

    @GetMapping("/get/{accountName}/{year}/{month}")
    public List<Operation> getOperationsFor(@PathVariable String accountName, @PathVariable int year, @PathVariable int month) {
        User user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        return accountService.getOperationsOfAccountAndDate(account, year, month);
    }

    @GetMapping("/get/{accountName}/{year}/{month}/{day}")
    public List<Operation> getOperationsFor(@PathVariable String accountName, @PathVariable int year, @PathVariable int month, @PathVariable int day) {
        User user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        return accountService.getOperationsOfAccountAndDate(account, year, month, day);
    }
}
