package com.example.hello_springbooot.modules.users.services.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
/*
Đoạn code này là một phần của hệ thống xác thực trong Spring Security. Khi người dùng đăng nhập, Spring Security
sẽ gọi service này để tìm thông tin người dùng dựa trên username (email). Service này kết nối với logic nghiệp
vụ của ứng dụng (thông qua UserService) để lấy thông tin người dùng từ cơ sở dữ liệu, sau đó chuyển đổi thành
đối tượng UserDetails mà Spring Security có thể hiểu được để xác thực và phân quyền.
*/

@Component("userDetailsService")
/*Khi chương trình gọi đến kiểu dữ liệu UserDetailsService (đây là 1 interface) thì nó sẽ gọi đến
UserDetailServiceCustom vì bây giờ UserDetailServiceCustom đã có vai trò như UserDetailsService */
public class UserDetailServiceCustom implements UserDetailsService {
    private final UserService userService;

    public UserDetailServiceCustom(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //tìm chính xác User bằng username trong database vì userService.handleFindUserbyEmail
        com.example.hello_springbooot.modules.users.entity.User getUser = userService.handleFindUserbyEmail(username);
        /*lấy thông tin người dùng thành công, trả về đối tượng User (do SpringBoot) định nghĩa để AuthoController
       làm việc tiếp, cụ thể là thực hiện encode, so sánh,....*/
        /*Có 1 điều ở đây khi chúng ta lấy mật khẩu của đối tượng getUser thì mật khẩu là mật khẩu mã hóa, nó sẽ kết
        hợp với mật khẩu mà chúng ta đã đưa ở bước trigger xem nó có phù hợp hay không*/
        //User là 1 hình của UserDetails nên có thể trả về kiểu User
        return new User(
                getUser.getEmail(),
                getUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
