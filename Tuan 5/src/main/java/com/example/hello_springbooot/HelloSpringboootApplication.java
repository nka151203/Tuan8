package com.example.hello_springbooot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
//tất cả mã nguồn sẽ sync chạy tổng hợp ở đây
public class HelloSpringboootApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringboootApplication.class, args);
	}
}
