package jp.co.jeus_blog.dto;

import jp.co.jeus_blog.repository.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TimeLinePostResponseDto {

    private Long id;
    private String boardNameColor;
    private String boardName;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public TimeLinePostResponseDto(Post post) {
        this.id = post.getId();
        this.boardName = post.getBoardName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }

    public TimeLinePostResponseDto(Post post, Long id, String content) {
        this.id = id;
        this.boardName = post.getBoardName();
        this.title = post.getTitle();
        this.content = content;
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }

    public TimeLinePostResponseDto(Post post, String boardNameColor) {
        this.id = post.getId();
        this.boardNameColor = boardNameColor;
        this.boardName = post.getBoardName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }
}
