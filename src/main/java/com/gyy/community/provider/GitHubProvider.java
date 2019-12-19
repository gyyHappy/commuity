package com.gyy.community.provider;

import com.alibaba.fastjson.JSON;
import com.gyy.community.dto.AccessTokenDTO;
import com.gyy.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author GYY
 * @date 2019/12/19 20:18
 */
@Component
public class GitHubProvider {

    /**
     * 获取并返回token
     * @param accessTokenDTO 用户的github信息
     * @return 返回token
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取并返回github用户信息
     * @param accessToken token
     * @return 返回github用户信息
     */
    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            return JSON.parseObject(string, GitHubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
