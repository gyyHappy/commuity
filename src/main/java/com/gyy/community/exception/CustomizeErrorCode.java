package com.gyy.community.exception;

/**
 * @author GYY
 * @date 2020/1/8 20:52
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    /**
     * 问题不存在
     */
    QUESTION_NOT_FOUND("你找的问题不存在了，要不换个试试？");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
