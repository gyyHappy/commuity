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

    private String clientId;

    private String clientSecret;

    private String code;

    private String redirectUri;

    private String state;
}
