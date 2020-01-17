package com.gyy.community.dto;

import lombok.Data;

/**
 * @author GYY
 * @date 2020/1/17 11:18
 */
@Data
public class NotificationDTO {

    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;
}
