package com.gyy.community.enums;

/**
 * @author GYY
 * @date 2020/1/16 19:38
 */
public enum NotificationStatusEnum {

    UNREAD(0),
    READ(1);

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
