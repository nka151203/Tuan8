package com.example.hello_springbooot.modules.users.controllers;
import com.example.hello_springbooot.modules.users.dtos.UserDTO;
import com.example.hello_springbooot.modules.users.dtos.LoginDTO;
import com.example.hello_springbooot.modules.users.dtos.respones.LoginResponse;
import com.example.hello_springbooot.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtils securityUtils;
    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtils securityUtils) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtils = securityUtils;
    }

    @PostMapping("login") //là 1 endpoint login
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO request) {
        //Tham khảo: https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html
        //Tham khảo hình ảnh flow: https://docs.spring.io/spring-security/reference/_images/servlet/authentication/architecture/abstractauthenticationprocessingfilter.png

        //Nạp thông tin vào Security bằng UsernamePasswordAuthenticationToken, dùng để 'trigger' Authentication authentication
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        //authenticationManagerBuilder.getObject().authenticate: kiểm tra username và passowrd có hợp lệ hay không ?
        //authenticate sẽ gọi đến UserDetails loadbyUsername: tìm người dùng ở database.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //authentication không lưu mật khẩu nếu nó xác nhận thành công, sau đó nó sẽ gen ra token cho client.
        //Tạo Token từ hàm đã viết
        String accessToken = this.securityUtils.createToken(authentication);
        LoginResponse res = new LoginResponse(accessToken, new UserDTO(request.getUsername()));
        return ResponseEntity.ok(res);
    }
}
