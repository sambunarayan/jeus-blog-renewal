package jp.co.jeus_blog.controller;

import jp.co.jeus_blog.dto.CurrentPostResponseDto;
import jp.co.jeus_blog.dto.PostResponseDto;
import jp.co.jeus_blog.service.ITBulletinBoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * IT Bulletin board controller
 */
@Log4j2
@RequestMapping("blog/it-bulletin")
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
        List<PostResponseDto> postList = boardService.findByBoardNameDesc(boardName);
        model.addAttribute("board_name", boardName);
        model.addAttribute("posts", postList);
        if (bno != null) {
            PostResponseDto resDto = boardService.findById(bno);
            if (resDto != null) {
                model.addAttribute("current_post", new CurrentPostResponseDto(resDto));
                if (page == null) {
                    int idx = binarySearch(postList, 0, postList.size(), resDto.getId());
                    page = ((idx + 1) / 10L) + 1;
                    long remain = (idx + 1) - (page * 10);
                    if (remain > 0) {
                        page++;
                    }
                }
            }
        }
        model.addAttribute("current_page", page == null ? 1 : page);
        return "it-board-post-list";
    }

    private int binarySearch(List<PostResponseDto> list, int s, int e, long id) {
        if (s > e) {
            return -1;
        }
        int mid = (s + e) / 2;
        if (list.get(mid).getId() == id) {
            return mid;
        } else {
            int idx = binarySearch(list, s, mid - 1, id);
            if (idx == -1) {
                return binarySearch(list, mid + 1, e, id);
            } else {
                return idx;
            }
        }
    }
}
