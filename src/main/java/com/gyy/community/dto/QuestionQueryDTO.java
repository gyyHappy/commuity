package com.gyy.community.dto;

import lombok.Data;

/**
 * @author GYY
 * @date 2020/1/21 11:27
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
