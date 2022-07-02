package cdu.gtj.community.provider;

import cdu.gtj.community.dto.AccessTokenDTO;
import cdu.gtj.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述
 *
 * @author wshg
 * @date 2022/07/01  10:30 AM
 */
@Component
public class GithubProvider {
    public String getAccess(AccessTokenDTO accessTokenDTO){
         MediaType json = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), json);
        System.out.println(JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
        try (Response response = client.newCall(request).execute()) {
            String str=response.body().string();
//                System.out.println(str);
                return str;
//                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public GithubUser getUser(String accesstoken){
        OkHttpClient client = new OkHttpClient();
        String accessToken="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";
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string=response.body().toString();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
