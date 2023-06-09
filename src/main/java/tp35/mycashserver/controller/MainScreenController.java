package tp35.mycashserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.User;
import tp35.mycashserver.response.MainScreenAccountResponse;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.AuthenticationService;
import tp35.mycashserver.service.MainScreenService;
import tp35.mycashserver.service.OperationsGetterService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Main screen", description = "Get operations list for main screen")
@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainScreenController {
    private final AuthenticationService authenticationService;
    private final MainScreenService mainScreenService;

    @Operation(summary = "Get map of account operations by month")
    @GetMapping("/get/{year}/{month}")
    public List<MainScreenAccountResponse> getAccountsOperationsMapFor(@PathVariable int year, @PathVariable int month) {
        User user = authenticationService.getAuthenticatedUser();
        return mainScreenService.getDataForMainScreen(user, year, month);
    }

    @Operation(summary = "Get map of account operations by month and day")
    @GetMapping("/get/{year}/{month}/{day}")
    public List<MainScreenAccountResponse> getAccountsOperationsMapFor(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        User user = authenticationService.getAuthenticatedUser();
        return mainScreenService.getDataForMainScreen(user, year, month, day);
    }
}
