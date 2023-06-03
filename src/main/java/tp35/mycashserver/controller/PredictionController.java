package tp35.mycashserver.controller;

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

@RestController
@RequestMapping("/api/predict")
@RequiredArgsConstructor
public class PredictionController {
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final PredictionService predictionService;

    @GetMapping("/{accountName}/{year}/{month}")
    public PredictionResponse getPrediction(@PathVariable String accountName, @PathVariable int year, @PathVariable int month) {
        User user = authenticationService.getAuthenticatedUser();
        Account account = accountService.getAccountByOwnerAndName(user, accountName);
        try {
            return predictionService.getPredictionFor(user, account, year, month);
        } catch (Exception e) {
            return new PredictionResponse(null, null, null, null);
        }
    }
}
