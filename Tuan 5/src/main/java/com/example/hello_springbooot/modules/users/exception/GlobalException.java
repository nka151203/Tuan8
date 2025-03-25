package com.example.hello_springbooot.modules.users.exception;

import com.example.hello_springbooot.modules.users.entity.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    //Đây là 1 thành phần của bắt Exception phạm vi trong 1 class.
    @ExceptionHandler(value = IDInvalidException.class)
    public ResponseEntity<RestResponse<Object>> handleIDException(IDInvalidException invalid) {
        //Lấy message từ cái Entity bắt được
        RestResponse<Object> resp = new RestResponse<>();
        resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
        resp.setMessage("Some ERROR has existed");
        resp.setError(invalid.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }
}
//@ControllerAdvice
//public class GlobalException {
//    //Đây là 1 thành phần của bắt Exception phạm vị loca
//    @ExceptionHandler(value = IDInvalidException.class)
//    @ResponseBody
//    public ResponseEntity<String> handleIDException(IDInvalidException invalid) {
//        //Lấy message từ cái Entity bắt được
//        return Resp hb  onseEntity.status(HttpStatus.CONFLICT).body(invalid.getMessage());
//    }
//}

