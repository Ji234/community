package cdu.gtj.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能描述
 *
 * @author wshg
 * @date 2022/06/30  8:05 PM
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String indexController(){
        return "index";
    }
}
