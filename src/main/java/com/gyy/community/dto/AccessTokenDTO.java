package com.gyy.community.dto;

import lombok.Data;

/**
 * @author GYY
 * @date 2019/12/19 20:19
 *
 * github accessToken信息实体类
 */

@Data
public class AccessTokenDTO {

    private String client_id;

    private String client_secret;

    private String code;

    private String redirect_uri;

    private String state;
}
