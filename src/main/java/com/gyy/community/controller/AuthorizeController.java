package com.gyy.community.controller;

import com.gyy.community.dto.AccessTokenDTO;
import com.gyy.community.dto.GitHubUser;
import com.gyy.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author GYY
 * @date 2019/12/19 20:12
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("14ce1bdacd596e4269ba");
        accessTokenDTO.setClient_secret("19254c78b6b2b7ebb885d5168e6d75c79f5d1d5d");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
