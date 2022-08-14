package jp.co.jeus_blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
