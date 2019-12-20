package com.gyy.community.model;

import lombok.Data;

/**
 * @author GYY
 * @date 2019/12/20 20:39
 */
@Data
public class User {

    /** id */
    private int id;

    /** uuid */
    private String accountId;

    /** 名称 */
    private String name;

    /** token */
    private String token;

    /** 创建时间 */
    private Long gmtCreate;

    /** 修改时间 */
    private Long gmtModified;
}
