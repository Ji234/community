package cdu.gtj.community.provider;

import cdu.gtj.community.dto.AccessTokenDTO;
import cdu.gtj.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.String.valueOf;

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
        String grant_type="authorization_code";
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("grant_type",grant_type);
        jsonObject.putAll((Map<? extends String, ?>) JSONObject.toJSON(accessTokenDTO));
        RequestBody body = RequestBody.create(jsonObject.toJSONString(), json);
//        System.out.println(JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://gitee.com/oauth/token")
                    .post(body)
                    .build();
        try (Response response = client.newCall(request).execute()) {
            String str=response.body().string();
            String accessToken=str.split(",")[0].split(":")[1].split("\"")[1];
            System.out.println(str);
                return accessToken;
//                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @Value("${gitee.userdata.api}")
    String userDataApi;
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
            String  strRequestUri=userDataApi+"?access_token="+accessToken;
        System.out.println(strRequestUri);
            Request request = new Request.Builder()
                    .url(strRequestUri)
                    .build();

            try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
