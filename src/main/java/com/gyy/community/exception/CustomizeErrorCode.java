package com.gyy.community.exception;

/**
 * @author GYY
 * @date 2020/1/8 20:52
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    /**
     * 问题不存在
     */
    QUESTION_NOT_FOUND(2001,"你找的问题不存在了，要不换个试试？"),

    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),

    NO_LOGIN(2003,"未登录，请先登录"),

    SYS_ERROR(2004,"服务器冒烟了，要不你访问别的吧！"),

    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在"),

    COMMENT_NOT_FOUND(2006,"你回复的评论不存在了，要不换个试试？"),

    COMMENT_IS_EMPTY(2007,"评论不能为空"),

    READ_NOTIFICATION_FAIL(2008,"兄弟你这是读别人的消息？"),

    NOTIFICATION_NOT_FOUND(2009,"消息不翼而飞了！！！");

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
