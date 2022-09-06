package jp.co.jeus_blog.controller;

import jp.co.jeus_blog.service.ITBulletinBoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * IT Bulletin board controller
 */
@Log4j2
@RequestMapping("blog/it-bulletin")
@Transactional
@Controller
public class ITBulletinBoardController {

    @Autowired
    private ITBulletinBoardService boardService;

    /**
     * Returns all board data.
     *
     * @return Returns a list of boards from the board table.
     */
    @RequestMapping(value = "boards", method = RequestMethod.GET)
    public String getBoards(Model model) {
        model.addAttribute("boards", boardService.getAllPosts());
        return "it-board";
    }
}
