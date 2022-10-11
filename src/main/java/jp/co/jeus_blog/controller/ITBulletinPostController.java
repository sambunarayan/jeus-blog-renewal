package jp.co.jeus_blog.controller;

import jp.co.jeus_blog.dto.CurrentPostResponseDto;
import jp.co.jeus_blog.dto.ITPostRegisterFormDto;
import jp.co.jeus_blog.dto.PostResponseDto;
import jp.co.jeus_blog.service.ITBulletinPostService;
import jp.co.jeus_blog.utils.PostSearchUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IT Bulletin post controller
 */
@Log4j2
@RequestMapping("blog/it-bulletin/post")
@Transactional
@Controller
public class ITBulletinPostController {

    @Autowired
    private ITBulletinPostService postService;

    /**
     * Get post form
     *
     * @param boardName board name
     * @param bno board number
     * @param page page
     * @param model Model
     * @return page of post registration
     */
    @RequestMapping(value = "/form/{board_name}", method = RequestMethod.GET)
    public String getPostForm(@PathVariable("board_name") String boardName,
                              @RequestParam(name = "bno", required = false) String bno,
                              @RequestParam(name = "page", required = false) String page,
                              Model model) {
        log.info("{}-bno:{},page:{}", boardName, bno, page);
        ITPostRegisterFormDto responseDto = new ITPostRegisterFormDto();
        if (bno != null) {
            PostResponseDto post = postService.findById(Long.valueOf(bno));
            responseDto = new ITPostRegisterFormDto(post);
        }
        responseDto.setBoardName(boardName);
        responseDto.setCurrentPage(page);
        model.addAttribute("form", responseDto);
        return "it-board-post-register";
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
    @GetMapping("/list/{boardName}")
    public String board(@PathVariable String boardName, @RequestParam(name = "bno", required = false) Long bno,
                        @RequestParam(name = "page", required = false) Long page, Model model) {
        log.info("{}-bno[{}]-page[{}]", boardName, bno, page);
        List<PostResponseDto> postList = postService.findByBoardNameDesc(boardName);
        model.addAttribute("board_name", boardName);
        if (bno != null) {
            PostResponseDto resDto = postService.findById(bno);
            if (resDto != null) {
                model.addAttribute("current_post", new CurrentPostResponseDto(resDto));
                if (page == null) {
                    int idx = PostSearchUtil.binarySearch(postList, 0, postList.size(), resDto.getId());
                    page = ((idx) / 10L) + 1;
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

    /**
     * Register post
     *
     * @param requestDto
     * @return redirect post list page
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String posting(ITPostRegisterFormDto requestDto) {
        long postId = 0l;
        String currPage = "&page=";
        if (requestDto.getId() != null) {
            log.info("Update post. [id={}]", requestDto.getId());
            currPage += requestDto.getCurrentPage();
        } else {
            log.info("New post to {} with [{}] title.", requestDto.getBoardName(), requestDto.getTitle());
            currPage += "1";
        }
        postId = postService.saveToPost(requestDto);
        return "redirect:/blog/it-bulletin/post/list/" + requestDto.getBoardName() + "?bno=" + postId + currPage;
    }

}
