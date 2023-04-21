package tp35.mycashserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp35.mycashserver.model.User;

@RestController
@RequestMapping("exa")
public class RegistrationController {
    @GetMapping("/mple")
    public User example() {
        return new User(123L, "Kikita");
    }
}

//localhost:8080/exa/mple
