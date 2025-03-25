package com.example.hello_springbooot.modules.users.dtos;

public class UserDTO {
    private final String email;

    public UserDTO(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }
}
