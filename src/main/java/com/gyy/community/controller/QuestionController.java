package com.gyy.community.controller;

import com.gyy.community.dto.CommentDTO;
import com.gyy.community.dto.QuestionDTO;
import com.gyy.community.enums.CommentEnum;
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
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        //查询回复列表
        List<CommentDTO> commentDTOList = commentService.listByTargetId(id, CommentEnum.QUESTION);
        //增加阅读数
        questionService.incView(id);
        model.addAttribute("comments",commentDTOList);
        model.addAttribute("question", questionDTO);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
