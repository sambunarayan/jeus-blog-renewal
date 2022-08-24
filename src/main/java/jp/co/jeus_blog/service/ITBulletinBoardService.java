package jp.co.jeus_blog.service;

import jp.co.jeus_blog.dto.BoardResponseDto;
import jp.co.jeus_blog.dto.ITPostListDto;
import jp.co.jeus_blog.dto.PostResponseDto;
import jp.co.jeus_blog.repository.BoardRepository;
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
public class ITBulletinBoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private PostRepository postRepository;

    /**
     * Returns all board data.
     *
     * @return Returns a list of boards from the board table.
     */
    @Transactional
    public List<BoardResponseDto> getAllPosts() {
        return boardRepository.findAll()
                .stream()
                .map(e -> new BoardResponseDto(e))
                .collect(Collectors.toList());
    }

    /**
     *
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
     *
     * @param id
     * @return
     */
    @Transactional
    public PostResponseDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No result record. id = " + id));
        return new PostResponseDto(post);
    }

    /**
     *
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
        int fromIdx = (page - 1) * 10;
        int toIdx = (page * 10) < list.size() ? (page * 10) : list.size();
        List<PostResponseDto> currPageList = list.subList(fromIdx, toIdx)
                .stream()
                .map(p -> new PostResponseDto(p))
                .collect(Collectors.toList());
        return new ITPostListDto(list.size(), currPageList);
    }
}
