package com.gyy.community.enums;

/**
 * @author GYY
 * @date 2020/1/16 19:38
 */
public enum NotificationTypeEnum {

    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");

    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }
}
