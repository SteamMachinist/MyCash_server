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
import tp35.mycashserver.response.PredictionResponse;
import tp35.mycashserver.service.AccountService;
import tp35.mycashserver.service.AuthenticationService;
import tp35.mycashserver.service.PredictionService;

@Tag(name = "Prediction", description = "Prediction for next month")
@RestController
@RequestMapping("/api/predict")
@RequiredArgsConstructor
public class PredictionController {
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final PredictionService predictionService;

    @Operation(summary = "Get response with calculated prediction for account for next month")
    @GetMapping("/{accountName}/{year}/{month}")
    public PredictionResponse getPrediction(@PathVariable String accountName, @PathVariable int year, @PathVariable int month) {
        User user = authenticationService.getAuthenticatedUser();
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        try {
            return predictionService.getPredictionFor(user, account, year, month);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
