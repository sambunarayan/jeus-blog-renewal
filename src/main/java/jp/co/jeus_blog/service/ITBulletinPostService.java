package jp.co.jeus_blog.service;

import jp.co.jeus_blog.dto.ITPostListDto;
import jp.co.jeus_blog.dto.ITPostRegisterFormDto;
import jp.co.jeus_blog.dto.PostResponseDto;
import jp.co.jeus_blog.repository.PostRepository;
import jp.co.jeus_blog.repository.entity.Post;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ITBulletinPostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * @param boardName
     * @return
     */
    @Transactional
    public List<PostResponseDto> findByBoardNameDesc(String boardName) {
        return postRepository.findByBoardNameDesc(boardName).stream()
                .map(p -> new PostResponseDto(p))
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Transactional
    public PostResponseDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No result record. id = " + id));
        return new PostResponseDto(post);
    }

    /**
     * @param boardName
     * @param page
     * @return
     */
    @Transactional
    public ITPostListDto findWithPaging(String boardName, int page) {
        List<Post> list = postRepository.findByBoardNameDesc(boardName);
        if (list == null || list.isEmpty()) {
            return ITPostListDto.builder()
                    .totalPostNum(0)
                    .posts(new ArrayList<>())
                    .build();
        }
        // paging
        int fromIdx = (page - 1) * 10;
        int toIdx = (page * 10) < list.size() ? (page * 10) : list.size();
        List<PostResponseDto> currPageList = list.subList(fromIdx, toIdx)
                .stream()
                .map(p -> new PostResponseDto(p))
                .collect(Collectors.toList());
        return new ITPostListDto(list.size(), currPageList);
    }

    /**
     * Save post
     *
     * @param requestDto
     * @return
     */
    @Transactional
    public Long saveToPost(ITPostRegisterFormDto requestDto) {
        return postRepository.save(Post.builder()
                .id(requestDto.getId())
                .title(requestDto.getTitle())
                .boardName(requestDto.getBoardName())
                .author("Administer")
                .content(requestDto.getContent())
                .build()).getId();
    }

    /**
     * Delete post
     *
     * @param id
     */
    @Transactional
    public void deletePost(long id) {
        Post postEntity = postRepository.findById(id).orElseThrow();
        postRepository.delete(postEntity);
        log.info("Post[{}] deleted.", postEntity.getId());
    }
}
