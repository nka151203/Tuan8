package com.example.hello_springbooot.modules.users.controllers;

import com.example.hello_springbooot.modules.users.entity.User;
import com.example.hello_springbooot.modules.users.exception.IDInvalidException;
import com.example.hello_springbooot.modules.users.services.impl.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService =  userService;
    }
    @PostMapping("/user/") //truyền data tới server
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        /*@RequestBody: Annotation sẽ giúp ánh xạ data được gửi từ client tới đối tượng, chú ý JSON được gửi phải
        trùng tên biến ứng với đối tượng được ánh xạ */
        //Controller tương tác với Controller
        /* Một lời gọi phản hồi hay 1 Respose Entity (HTTP Response) sẽ gồm 3 phần:
        HEADER, BODY (phía client bắt), STATUS-CODE. Vì vậy trả về ResposeEntity
        * */
        User returnUser = this.userService.handleCreateEncoderUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnUser);
    }
    @PostMapping("/user/createwithEncoder")
    public ResponseEntity<User> createNewUserwithEncoder(@RequestBody User user) {
        User returnUser = this.userService.handleCreateEncoderUser(user);
        return ResponseEntity.ok(returnUser);
    }
    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
      /* @PathVariable có thể được sử dụng để xử lý các biến mẫu
      trong ánh xạ URI yêu cầu và đặt chúng làm tham số phương thức. */
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok("Successful Delete User " + id);
//        = return ResponseEntity.status(HttpStatus.OK).body("Successful Delete User " + id);
    }
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserID(@PathVariable("id") Long id) throws IDInvalidException {
        if (id > 20) {
            //nếu chỉ có phần này thì ngoại lệ chỉ được thông báo ở termianl
            throw new IDInvalidException("Mã số bạn tìm không hợp lệ, mã số nhỏ hơn 20 he");
        }
        User user = this.userService.handleFindByID(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping("user/all")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(this.userService.handleFindAllUser());
    }
    @PutMapping("user/") //PUT ~ PATCH
    public ResponseEntity<User> updateUserID(@RequestBody User userUpdate) {
        return ResponseEntity.ok(this.userService.handleUpdateUser(userUpdate));
    }
}
