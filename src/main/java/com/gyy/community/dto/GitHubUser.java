package com.gyy.community.dto;

import lombok.Data;

/**
 * @author GYY
 * @date 2019/12/19 20:48
 *
 * github信息实体类
 */
@Data
public class GitHubUser {
    /**
     * 姓名
     */
    private String name;
    /**
     * id
     */
    private Long id;
    /**
     * 个性签名
     */
    private String bio;
}
