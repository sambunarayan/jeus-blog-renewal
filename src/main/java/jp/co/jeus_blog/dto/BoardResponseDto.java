package jp.co.jeus_blog.dto;

import jp.co.jeus_blog.repository.entity.Board;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class BoardResponseDto implements Serializable {

    private Long id;
    private String boardName;
    private String category;
    private String color;
    private String logo;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.boardName = board.getBoardName();
        this.category = board.getCategory();
        this.color = board.getColor();
        this.logo = board.getLogo();
        this.description = board.getDescription();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }
}
