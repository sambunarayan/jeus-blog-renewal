package jp.co.jeus_blog.service;

import jp.co.jeus_blog.dto.BoardResponseDto;
import jp.co.jeus_blog.dto.ITBoardRegisterFormDto;
import jp.co.jeus_blog.dto.ITPostListDto;
import jp.co.jeus_blog.dto.PostResponseDto;
import jp.co.jeus_blog.repository.BoardRepository;
import jp.co.jeus_blog.repository.PostRepository;
import jp.co.jeus_blog.repository.entity.Board;
import jp.co.jeus_blog.repository.entity.Post;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * IT Bulletin board service
 */
@Log4j2
@Service
public class ITBulletinBoardService {

    @Autowired
    private BoardRepository boardRepository;

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

    @Transactional
    public void saveToBoard(ITBoardRegisterFormDto form, String logoName) {
        Board boardEntity = boardRepository.save(Board.builder()
                .boardName(form.getBoardName())
                .category(form.getCategory())
                .color(form.getColorSelect().replace("bg", "text"))
                .description(form.getDetailsArea())
                .logo(logoName)
                .build());
        log.info("{} board registration completed. [id={}]", boardEntity.getBoardName(), boardEntity.getId());
    }
}
