package com.gyy.community.enums;

/**
 * @author GYY
 * @date 2020/1/10 21:24
 */
public enum CommentEnum {
    /**
     *
     */
    QUESTION(1),
    COMMENT(2);

    public static boolean isExit(Integer type) {
        for(CommentEnum commentEnum : CommentEnum.values()){
            if (commentEnum.getType().equals(type)){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    private Integer type;

    CommentEnum(Integer type) {
        this.type = type;
    }
}
