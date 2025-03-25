package com.example.hello_springbooot.utils;

import com.example.hello_springbooot.modules.users.entity.RestResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//Lớp này để thực hiện cấu hình lại RestResponse mà Spring trả về cho người dùng ở tất cả phương thức

@RestControllerAdvice //Áp dụng cho tất cả các RestController
public class FormatRestResponse implements ResponseBodyAdvice<Object> {
    //implements ResponseBodyAdvice<Object> : can thiệp và thay đổi response body trước khi trả về client
    //Có phải tất cả phương thức trả về đều áp dụng Format Response không ? -> Có: return True.
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
    //beforeBodyWrite: trước khi Spring viết format thì hãy format lại nhanh và luôn bằng hàm này.
    /**
    @body: body thông thường mà API trả về
    @request: yêu cầu của client, có thể lấy được phương thức gọi của client.
    @response: phần trả về của server
    */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = servletResponse.getStatus();

        RestResponse<Object> resp =  new RestResponse<>();
        resp.setStatusCode(status);
        if (status >= 400) {
            return body;
            //đối với status >= 400 là lỗi thì ta có cách xử lý riêng bởi cách trả như chúng ta làm không tiện lắm
            //ta tiến hành sửa đổi chính body của exception khi ném ra exception
        } else {
            resp.setMessage("CALL API OK");
            resp.setData(body);
        }
        return resp;
    }
}

