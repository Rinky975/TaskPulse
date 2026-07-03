package taskpulse.service;

import taskpulse.dto.LoginRequest;
import taskpulse.dto.LoginResponse;
import taskpulse.entity.User;

public interface UserService {

    User registerUser(User user);

    LoginResponse login(LoginRequest request);
}