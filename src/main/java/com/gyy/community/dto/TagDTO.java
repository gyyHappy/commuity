package com.gyy.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author GYY
 * @date 2020/1/16 10:02
 */
@Data
public class TagDTO {

    private String categoryName;

    private List<String> tags;
}
