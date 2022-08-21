package jp.co.jeus_blog.controller;

import jp.co.jeus_blog.service.ITBulletinBoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@RequestMapping("it-bulletin")
@Controller
public class ITBulletinBoardController {

    @Autowired
    private ITBulletinBoardService service;

    /**
     * Returns all board data.
     *
     * @return Returns a list of boards from the board table.
     */
    @RequestMapping(value ="boards", method= RequestMethod.GET)
    public String getBoards(Model model) {
        model.addAttribute("boards", service.getAllPosts());
        return "it-board";
    }
}
