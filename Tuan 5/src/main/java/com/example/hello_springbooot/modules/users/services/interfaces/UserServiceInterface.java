package com.example.hello_springbooot.modules.users.services.interfaces;
import com.example.hello_springbooot.modules.users.dtos.respones.LoginResponse;
import com.example.hello_springbooot.modules.users.dtos.requests.loginRequest;
import com.example.hello_springbooot.modules.users.entity.User;

public interface UserServiceInterface {
    LoginResponse login(loginRequest request);
    User handleCreateUser(User user);

    User handleFindUserbyEmail(String username);
}
