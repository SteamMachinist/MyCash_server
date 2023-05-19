package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/user")
    public String saveUser(@RequestHeader("Authorization") String authorization) {
        String token = authorization.split(" ")[1];
        System.out.println(token);
        userService.addUser(new User(null, null, token));
        return "success";
    }
}
