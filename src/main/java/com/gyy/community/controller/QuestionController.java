package com.gyy.community.controller;

import com.gyy.community.dto.CommentDTO;
import com.gyy.community.dto.QuestionDTO;
import com.gyy.community.service.CommentService;
import com.gyy.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author GYY
 * @date 2020/1/5 11:15
 */
@Controller
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        //查询回复列表
        List<CommentDTO> commentDTOList = commentService.listByQuestionId(id);
        //增加阅读数
        questionService.incView(id);
        model.addAttribute("commentList",commentDTOList);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
