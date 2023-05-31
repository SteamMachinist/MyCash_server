package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainScreenController {
    private final UserService userService;
    private final AccountService accountService;
    private final OperationMapper operationMapper;

    @GetMapping("/get/{accountName}")
    public List<OperationDTO> getOperationsFor(@PathVariable String accountName) {
        User user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        return operationMapper.toOperationDTOs(accountService.getOperationsOfAccount(account));
    }

    @GetMapping("/get/{accountName}/{year}/{month}")
    public List<OperationDTO> getOperationsFor(@PathVariable String accountName, @PathVariable int year, @PathVariable int month) {
        User user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        return operationMapper.toOperationDTOs(accountService.getOperationsOfAccountAndDate(account, year, month));
    }

    @GetMapping("/get/{accountName}/{year}/{month}/{day}")
    public List<OperationDTO> getOperationsFor(@PathVariable String accountName, @PathVariable int year, @PathVariable int month, @PathVariable int day) {
        User user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(user.getUsername());
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        System.out.println(account.getName());
        List<OperationDTO> operationDTOS = operationMapper.toOperationDTOs(accountService.getOperationsOfAccountAndDate(account, year, month, day));
        operationDTOS.forEach(System.out::println);
        return operationDTOS;
    }
}
