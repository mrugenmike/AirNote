package com.airnote.services.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectTokenException.class)
    public ResponseEntity<Map<String,Object>> handleIncorrectTokenException(IncorrectTokenException exception){
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("message",exception.getMessage());
        response.put("accessToken",exception.getAccessToken());
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
    }
}
