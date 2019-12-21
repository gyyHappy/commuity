package com.gyy.community.controller;

import com.gyy.community.dto.AccessTokenDTO;
import com.gyy.community.dto.GitHubUser;
import com.gyy.community.mapper.UserMapper;
import com.gyy.community.model.User;
import com.gyy.community.provider.GitHubProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author GYY
 * @date 2019/12/19 20:12
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String uri;

    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * github回调获取登录用户信息
     * @param code code
     * @param state state
     * @param request request
     * @return 返回首页
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        //将信息放入accessTokenDTO中
        AccessTokenDTO accessTokenDTO = setAccessTokenDTO(code, state);
        //获取token
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        //获取github用户信息
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        //判断用户信息是否为空
        if (gitHubUser != null) {
            //用户信息不为空，登录成功，重定向到首页
            User user = new User();
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setName(gitHubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //登录成功，写cookie，写session
            request.getSession().setAttribute("user",gitHubUser);
            //重定向到首页
            return "redirect:/";
        }else {
            //用户信息为空，登录失败，返回首页
            return "redirect:/";
        }
    }

    /**
     * 设置AccessTokenDTO的信息
     * @param code code
     * @param state state
     * @return 返回AccessTokenDTO对象
     */
    @NotNull
    private AccessTokenDTO setAccessTokenDTO(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(uri);
        accessTokenDTO.setState(state);
        return accessTokenDTO;
    }
}
