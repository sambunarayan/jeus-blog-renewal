package jp.co.jeus_blog.service;

import jp.co.jeus_blog.dto.BoardResponseDto;
import jp.co.jeus_blog.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ITBulletinBoardService {

    @Autowired
    private BoardRepository repository;

    /**
     * Returns all board data.
     *
     * @return Returns a list of boards from the board table.
     */
    @Transactional
    public List<BoardResponseDto> getAllPosts() {
        return repository.findAll()
                .stream()
                .map(e -> new BoardResponseDto(e))
                .collect(Collectors.toList());
    }
}
