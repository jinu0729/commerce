package com.jinu.commerceproductservice.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<Object> handleApiRequestException(CustomException ex) {
        Integer status = ex.getErrorCode().getStatus();
        String code = ex.getErrorCode().getCode();
        String message = ex.getErrorCode().getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(status);
        exceptionResponse.setCode(code);
        exceptionResponse.setMessage(message);


        System.out.println("ERR : " + status + "," + code + "," + message);

        return new ResponseEntity<>(
                exceptionResponse,
                HttpStatus.valueOf(ex.getErrorCode().getStatus())
        );
    }

}
