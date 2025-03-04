package com.example.hello_springbooot.modules.users.services.impl;
import com.example.hello_springbooot.modules.users.dtos.requests.loginRequest;
import com.example.hello_springbooot.modules.users.dtos.respones.LoginResponse;
import com.example.hello_springbooot.modules.users.entity.User;
import com.example.hello_springbooot.modules.users.repositories.UserRepository;
import com.example.hello_springbooot.modules.users.services.interfaces.UserServiceInterface;
import com.example.hello_springbooot.service.BaseService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/* Đây là UserService, thực hiện các logic, trả về cho Controller */
@Service
public class UserService extends BaseService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; //không cần khởi tạo vì DI ở lớp Security Config
    }


    @Override
    public LoginResponse login(loginRequest request) {
        return null;
    }

    //Service tương tác với Repository
    @Override
    public User handleCreateUser(User user) {
        return userRepository.save(user);
    }

    public void handleDeleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User handleFindByID(Long id) {
        Optional<User> handleFindByID =  userRepository.findById(id);
        return handleFindByID.orElse(null);
    }
    public List<User> handleFindAllUser() {
        return userRepository.findAll();
    }

    public User handleUpdateUser(User userUpdate) {
        Optional<User> needUpdate = userRepository.findById(userUpdate.getId());
        if (needUpdate.isPresent()) {
            User user = needUpdate.get();
            user.setName(userUpdate.getName());
            user.setEmail(userUpdate.getEmail());
            user.setPassword(userUpdate.getPassword());
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public User handleCreateEncoderUser(User user) {
        String currPass = user.getPassword();
        String encoderPass = passwordEncoder.encode(currPass);
        user.setPassword(encoderPass);
        this.userRepository.save(user);
        return user;
    }
    public User handleFindUserbyEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
