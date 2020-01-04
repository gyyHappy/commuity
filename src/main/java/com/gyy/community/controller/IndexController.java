package com.gyy.community.controller;

import com.gyy.community.dto.PaginationDTO;
import com.gyy.community.dto.QuestionDTO;
import com.gyy.community.mapper.UserMapper;
import com.gyy.community.model.User;
import com.gyy.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author GYY
 * @date 2019/12/5 20:38
 */
@Controller
public class IndexController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private QuestionService questionService;

    /**
     * 查询数据库是否存在当前cookie
     * @param request request
     * @return 登录界面
     */
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "2") Integer size){
        //获取cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (("token").equals(cookie.getName())){
                    String token = cookie.getValue();
                    //通过token查询数据库
                    User user = userMapper.selectByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
