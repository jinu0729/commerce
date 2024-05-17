package com.jinu.commerceproductservice.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBodyDto {
    private int status;
    private String code;
    private String message;
    private Object result;


    @Builder
    public ResponseBodyDto(int status, String code, String message, Object result) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static ResponseBodyDto success(String message) {
        return builder()
                .status(HttpStatus.OK.value())
                .code(HttpStatus.OK.getReasonPhrase())
                .message(message)
                .build();
    }

    public static ResponseBodyDto successWithResult(String message, Object result) {
        return builder()
                .status(HttpStatus.OK.value())
                .code(HttpStatus.OK.getReasonPhrase())
                .message(message)
                .result(result)
                .build();
    }
}