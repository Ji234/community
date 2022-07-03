package cdu.gtj.community.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述
 *
 * @author wshg
 * @date 2022/06/30  8:05 PM
 */
@Controller
public class IndexController {
    @Value("${gitee.authorize.uri}")
    private String authorizeUri;
    @Value("${gitee.client.id}")
    private String clientId;
    @Value("${gitee.redirect.uri}")
    private String redirctUri;
    @GetMapping( value = {"index","/"})
    public String indexController(Model model ){
        model.addAttribute("authorize_uri",this.authorizeUri);
        model.addAttribute("client_id",this.clientId);
        model.addAttribute("redirct_uri",this.redirctUri);
        return "index";
    }
}
