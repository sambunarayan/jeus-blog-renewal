package jp.co.jeus_blog.controller;

import jp.co.jeus_blog.dto.TimeLineMainPageResponseDto;
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
@RequiredArgsConstructor
@RequestMapping("blog")
@Controller
public class MainController {

    @Autowired
    private HttpSession session;

    @RequestMapping(value="time-line", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        log.info("time-line start");
        TimeLineMainPageResponseDto res = new TimeLineMainPageResponseDto();
        res.setLastIndexId(Integer.MAX_VALUE);
        model.addAttribute("timeLine", res);
        return "time-line";
    }
}
