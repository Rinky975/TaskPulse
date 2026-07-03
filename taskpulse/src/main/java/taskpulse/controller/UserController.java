package taskpulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import taskpulse.dto.LoginRequest;
import taskpulse.dto.LoginResponse;
import taskpulse.entity.User;
import taskpulse.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return "User Controller Working";
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
    @GetMapping("/profile")
public String profile() {
    return "Welcome! JWT Authentication Successful.";
}
}