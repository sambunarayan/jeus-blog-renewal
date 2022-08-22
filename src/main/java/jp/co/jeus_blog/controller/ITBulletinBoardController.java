package jp.co.jeus_blog.controller;

import jp.co.jeus_blog.dto.CurrentPostResponseDto;
import jp.co.jeus_blog.dto.PostResponseDto;
import jp.co.jeus_blog.service.ITBulletinBoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping("it-bulletin")
@Controller
public class ITBulletinBoardController {

    @Autowired
    private ITBulletinBoardService boardService;

    /**
     * Returns all board data.
     *
     * @return Returns a list of boards from the board table.
     */
    @RequestMapping(value ="boards", method= RequestMethod.GET)
    public String getBoards(Model model) {
        model.addAttribute("boards", boardService.getAllPosts());
        return "it-board";
    }

    /**
     * Get board by name
     *
     * @param boardName
     * @param bno
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/post/list/{boardName}")
    public String board(@PathVariable String boardName, @RequestParam(name = "bno", required = false) Long bno,
                        @RequestParam(name = "page", required = false) Long page, Model model) {
        model.addAttribute("board_name", boardName);
        model.addAttribute("posts", boardService.findByBoardNameDesc(boardName));
        if (bno != null) {
            PostResponseDto resDto = boardService.findById(bno);
            if (resDto != null) {
                model.addAttribute("current_post", new CurrentPostResponseDto(resDto));
            }
        }
        model.addAttribute("current_page", page == null ? 1 : page);
        return "it-board-post-list";
    }
}
