package com.gyy.community.controller;

import com.gyy.community.dto.PaginationDTO;
import com.gyy.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author GYY
 * @date 2019/12/5 20:38
 */
@Controller
public class IndexController {


    @Resource
    private QuestionService questionService;

    /**
     * 首页
     * @param model model
     * @param page  当前页
     * @param size  当前页显示的条数
     * @return 返回首页
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "3") Integer size){
        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";

    }
}
