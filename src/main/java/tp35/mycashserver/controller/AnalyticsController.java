package tp35.mycashserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.User;
import tp35.mycashserver.response.AnalyticsResponse;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.AnalyticsService;
import tp35.mycashserver.service.AuthenticationService;

@Tag(name = "Analytics", description = "Analytics data for charts")
@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final AnalyticsService analyticsService;

    @Operation(summary = "Get analytics data for user's account for month (expected to be next month)")
    @GetMapping("/{accountName}/{year}/{month}")
    public AnalyticsResponse getAnalytics(@PathVariable String accountName, @PathVariable int year, @PathVariable int month) {
        User user = authenticationService.getAuthenticatedUser();
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        return analyticsService.getAnalyticsFor(user, account, year, month);
    }
}
