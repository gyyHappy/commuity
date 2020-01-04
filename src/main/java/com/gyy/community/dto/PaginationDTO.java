package com.gyy.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GYY
 * @date 2020/1/4 8:59
 */
@Data
public class PaginationDTO {

    private List<QuestionDTO> questions;

    private Boolean showPrevious;

    private Boolean showFirstPage;

    private Boolean showNext;

    private Boolean showEndPage;

    private Integer page;

    private List<Integer> pages = new ArrayList<>();

    private Integer totalPage;

    public void set(Integer page, Integer size, Integer count) {


        //判断当前应该有的页数
        if (count % size == 0) {
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }

        if (page < 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        this.page = page;

        //显示的页数
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //判断是否有上一页
        showPrevious = page != 1;

        //判断是否有下一页
        showNext = !page.equals(totalPage);

        //判断是否显示第一页
        showFirstPage = !pages.contains(1);

        //判断是否显示最后一页
        showEndPage = !pages.contains(totalPage);
    }
}
