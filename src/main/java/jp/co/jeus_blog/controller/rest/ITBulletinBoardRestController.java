package jp.co.jeus_blog.controller.rest;

import jp.co.jeus_blog.dto.ITPostListDto;
import jp.co.jeus_blog.service.ITBulletinPostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
