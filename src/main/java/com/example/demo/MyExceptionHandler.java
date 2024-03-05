package com.example.demo;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//使用@ControllerAdvice與@ExceptionHandler，共用錯誤時的處理方法
//@ControllerAdvice使用AOP功能實作

@ControllerAdvice
public class MyExceptionHandler {
    private String errMsg;

    //找不到針對該錯誤進行處理的method，會往父類去找有沒有處理
    //例:IllegalArgumentException，查找方向:IllegalArgumentException > RuntimeException > Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(Exception exception){
        errMsg = ExceptionUtils.getStackTrace(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.errMsg);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handler(RuntimeException exception){
//        errMsg = this.getErrMsg(exception);
//        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(this.errMsg);
//    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handler(IllegalArgumentException exception){
//        errMsg = this.getErrMsg(exception);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.errMsg);
//    }

    private String getErrMsg(Exception exception){
        return exception.getClass().getSimpleName() + ": " + exception.getMessage();
    }
}
