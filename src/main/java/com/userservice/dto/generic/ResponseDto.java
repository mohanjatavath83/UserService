package com.userservice.dto.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private Boolean success;
    private String message;
    private Integer statusCode;
    T result;

    public ResponseDto(T result) {
        this.result = result;
    }

    public ResponseDto(Boolean success, String message, Integer statusCode) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
    }
}
