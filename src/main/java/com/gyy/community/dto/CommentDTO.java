package com.gyy.community.dto;

import lombok.Data;

/**
 * @author GYY
 * @date 2020/1/10 20:12
 */
@Data
public class CommentDTO {

    private Long parentId;

    private Integer type;

    private String content;
}
