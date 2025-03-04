package com.example.hello_springbooot.modules.users.dtos.respones;

import com.example.hello_springbooot.modules.users.dtos.UserDTO;


/*Đây là trả về khi Login thành công, trả về bao gồm: TOKEN và UserDTO (1 dạng bảo vệ dữ liệu User)*/
public class LoginResponse {
    private final String token;
    private final UserDTO user;

    public LoginResponse(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserDTO getUser() {
        return user;
    }

}
