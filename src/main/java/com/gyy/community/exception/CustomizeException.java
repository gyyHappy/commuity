package com.gyy.community.exception;

/**
 * @author GYY
 * @date 2020/1/8 20:24
 */
public class CustomizeException extends RuntimeException{

    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
