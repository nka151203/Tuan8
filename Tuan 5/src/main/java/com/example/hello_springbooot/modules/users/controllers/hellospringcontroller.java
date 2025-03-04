package com.example.hello_springbooot.modules.users.controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//thông báo đây là 1 controller và đính API vào class này
/*
1. Spring Boot quét tất cả các class có annotation @RestController.
2. Khi phát hiện hellospringcontroller, nó kiểm tra xem cần truyền gì vào constructor.
3. Spring thấy JdbcTemplate, nên nó tìm xem có instance nào của JdbcTemplate đã được tạo chưa.
4. Nếu JdbcTemplate chưa có, Spring Boot tự động tạo nó từ các thiết lập database.
5. Spring gọi constructor của hellospringcontroller và truyền JdbcTemplate vào.
6. Cntroller được khởi tạo và sẵn sàng xử lý request HTTP.
 */
@RestController
@RequestMapping("/api")
public class hellospringcontroller {
    private final JdbcTemplate jdbcTemplate;
    public hellospringcontroller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @GetMapping("hello")
    String  sayHello() {
        String firstSQL = "CREATE TABLE IF NOT EXISTS table1 (" +
                "id INT, " +
                "name VARCHAR(50), " +
                "classname VARCHAR(4)" +
                ");";
        jdbcTemplate.execute(firstSQL);
        return "Hello First Boot 3";
    }
}
