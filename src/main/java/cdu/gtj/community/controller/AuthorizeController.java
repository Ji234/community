package cdu.gtj.community.controller;

import cdu.gtj.community.dto.AccessTokenDTO;
import cdu.gtj.community.dto.GithubUser;
import cdu.gtj.community.mapper.UserMapper;
import cdu.gtj.community.model.User;
import cdu.gtj.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 功能描述
 *
 * @author wshg
 * @date 2022/07/01  10:22 AM
 */

@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state ,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        //init
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        String str = githubProvider.getAccess(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(str);

        if (githubUser != null) {
            User user = new User();

            //init
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(githubUser.getId().toString());
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            userMapper.inster(user);
            //login success write session and cookie
            request.getSession().setAttribute("user", githubUser);
            return "redirect:index";
        } else {
            //login error ,login again
            return "redirect:index";
        }

    }
}
