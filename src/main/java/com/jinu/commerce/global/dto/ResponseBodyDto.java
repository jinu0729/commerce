package com.jinu.commerce.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBodyDto {
    private int status;
    private String code;
    private String message;
    private Object data;


    @Builder
    public ResponseBodyDto(int status, String code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseBodyDto success(String message) {
        return ResponseBodyDto.builder()
                .status(HttpStatus.OK.value())
                .code(HttpStatus.OK.getReasonPhrase())
                .message(message)
                .build();
    }

    public ResponseBodyDto successWithData(String message, Object data) {
        return ResponseBodyDto.builder()
                .status(HttpStatus.OK.value())
                .code(HttpStatus.OK.getReasonPhrase())
                .message(message)
                .data(data)
                .build();
    }
}