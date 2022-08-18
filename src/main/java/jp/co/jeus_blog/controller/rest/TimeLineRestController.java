package jp.co.jeus_blog.controller.rest;

import jp.co.jeus_blog.dto.TimeLinePostResponseDto;
import jp.co.jeus_blog.service.TimeLineService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RequestMapping("blog-rest/time-line")
@RestController
public class TimeLineRestController {

    @Autowired
    private TimeLineService service;

    /**
     * Get latest posts
     *
     * @param id id of post
     * @return List of post
     */
    @GetMapping("/latest/{id}")
    public List<TimeLinePostResponseDto> getLatestPosts(@PathVariable("id") Long id) {
        return service.findLatestPost(id);
    }
}
