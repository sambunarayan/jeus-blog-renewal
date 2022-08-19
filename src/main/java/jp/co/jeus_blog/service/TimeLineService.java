package jp.co.jeus_blog.service;

import jp.co.jeus_blog.dto.TimeLinePostResponseDto;
import jp.co.jeus_blog.repository.BoardRepository;
import jp.co.jeus_blog.repository.PostRepository;
import jp.co.jeus_blog.repository.entity.Post;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TimeLine business logic
 */
@Log4j2
@Service
public class TimeLineService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BoardRepository boardRepository;

    /**
     *
     * @return
     */
    @Transactional
    public List<TimeLinePostResponseDto> findLatestPost(Long id) {
        List<TimeLinePostResponseDto> latestPosts = new ArrayList<>();
        List<Post> posts = id <= 0 ? postRepository.findAllDesc() : postRepository.findLatestPosts(id);
        Map<String, String> colorMap = boardRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(b -> b.getBoardName(), b -> b.getColor()));
        log.debug(posts);
        for (Post post : posts) {
            latestPosts.add(new TimeLinePostResponseDto(post, colorMap.get(post.getBoardName())));
            if (latestPosts.size() >= 5) {
                break;
            }
        }
        return latestPosts;
    }
}
