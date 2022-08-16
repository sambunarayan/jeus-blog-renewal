package jp.co.jeus_blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String showMainPage() {
        return "index";
    }
}
