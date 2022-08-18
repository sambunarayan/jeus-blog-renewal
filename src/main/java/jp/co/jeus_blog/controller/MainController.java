package jp.co.jeus_blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Log4j2
@PropertySource("classpath:message.property")
@RequiredArgsConstructor
@RequestMapping("blog")
@Controller
public class MainController {

    @Value("${main.title.message}")
    private String mainTitle;
    @Value("${main.title.detail.message}")
    private String mainTitleDetail;
    @Value("${profil.aboutme}")
    private String aboutMe;
    @Autowired
    private HttpSession session;

    @RequestMapping(value="time-line", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("lastIndexId", Integer.MAX_VALUE);
        model.addAttribute("aboutMe", aboutMe);
        model.addAttribute("mainTitle", mainTitle);
        model.addAttribute("mainTitleDetail", mainTitleDetail);
        return "index";
    }
}
