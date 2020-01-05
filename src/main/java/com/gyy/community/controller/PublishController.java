package com.gyy.community.controller;

import com.gyy.community.model.Question;
import com.gyy.community.mapper.QuestionMapper;
import com.gyy.community.mapper.UserMapper;
import com.gyy.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author GYY
 * @date 2019/12/21 15:54
 */
@Controller
public class PublishController {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question,
                            HttpServletRequest request,
                            Model model){

        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());

        if (question.getTitle() == null || "".equals(question.getTitle())) {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (question.getTag() == null || "".equals(question.getTag())) {
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        if (question.getDescription() == null || "".equals(question.getDescription())) {
            model.addAttribute("error","内容不能为空");
            return "publish";
        }

        //获取用户的信息
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //将信息放到前端
            model.addAttribute("error","用户未登录");
        }else {
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }
        return "publish";
    }
}
