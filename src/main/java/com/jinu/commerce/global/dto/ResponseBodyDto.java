package com.jinu.commerce.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBodyDto {
    private String message;
    private Object data;


    @Builder
    public ResponseBodyDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseBodyDto success(String message) {
        return ResponseBodyDto.builder()
                .message(message)
                .build();
    }

    public ResponseBodyDto successWithData(String message, Object data) {
        return ResponseBodyDto.builder()
                .message(message)
                .data(data)
                .build();
    }

    public ResponseBodyDto fail(String message) {
        return ResponseBodyDto.builder()
                .message(message)
                .build();
    }
}