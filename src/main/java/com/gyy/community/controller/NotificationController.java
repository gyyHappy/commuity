package com.gyy.community.controller;

import com.gyy.community.dto.NotificationDTO;
import com.gyy.community.dto.PaginationDTO;
import com.gyy.community.enums.NotificationTypeEnum;
import com.gyy.community.model.User;
import com.gyy.community.service.NotificationService;
import com.gyy.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author GYY
 * @date 2020/1/4 20:44
 */
@Controller
public class NotificationController {


    @Resource
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,
                          HttpServletRequest request) {
        //获取用户的信息
        User user = (User) request.getSession().getAttribute("user");
        //如果没有登录就重定向到首页
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id,user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/";
        }
    }
}
