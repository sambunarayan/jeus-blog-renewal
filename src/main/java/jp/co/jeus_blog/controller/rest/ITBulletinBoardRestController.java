package jp.co.jeus_blog.controller.rest;

import jp.co.jeus_blog.dto.ITPostListDto;
import jp.co.jeus_blog.service.ITBulletinPostService;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping("blog-rest/it-bulletin")
@RestController
public class ITBulletinBoardRestController {

    @Autowired
    private ITBulletinPostService service;

    /**
     * Get posts with page
     *
     * @param boardName
     * @param page
     * @return
     */
    @GetMapping("/{boardName}/page/{page}")
    public ITPostListDto getPostPage(@PathVariable("boardName") String boardName, @PathVariable("page") int page) {
        return service.findWithPaging(boardName.toLowerCase(), page);
    }

    @DeleteMapping("/{boardName}/delete/{id}")
    public String deletePost(@PathVariable("boardName") String boardName, @PathVariable("id") long id) {
        log.info("Delete post. [id={}, board={}]", id, boardName);
        service.deletePost(id);
        return String.valueOf(id);
    }
}
