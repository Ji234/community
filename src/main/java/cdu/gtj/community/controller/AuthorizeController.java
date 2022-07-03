package cdu.gtj.community.controller;

import cdu.gtj.community.dto.AccessTokenDTO;
import cdu.gtj.community.dto.GithubUser;
import cdu.gtj.community.mapper.UserMapper;
import cdu.gtj.community.model.User;
import cdu.gtj.community.provider.GithubProvider;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.UUID;

/**
 * 功能描述
 *
 * @author wshg
 * @date 2022/07/01  10:22 AM
 */

@Controller
public class AuthorizeController {

    @Value("${gitee.client.id}")
    private String clientId;

    @Value("${gitee.client.secret}")
    private String clientSecret;

    @Value("${gitee.redirect.uri}")
    private String redirectUri;
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           HttpServletRequest request , HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        //init
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccess(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null) {
            User user = new User();

            String token =UUID.randomUUID().toString();
            //init
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(githubUser.getId().toString());
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            userMapper.inster(user);
            //login success write session and cookie
            request.getSession().setAttribute("user", githubUser);

            response.addCookie(new Cookie("token",token));
            return "redirect:index";
        } else {
            //login error ,login again
            return "redirect:index";
        }

    }
}
