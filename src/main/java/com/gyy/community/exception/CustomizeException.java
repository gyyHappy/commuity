package com.gyy.community.exception;

/**
 * @author GYY
 * @date 2020/1/8 20:24
 */
public class CustomizeException extends RuntimeException{

    private String message;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
