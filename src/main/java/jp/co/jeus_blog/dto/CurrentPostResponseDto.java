package jp.co.jeus_blog.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CurrentPostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CurrentPostResponseDto(PostResponseDto resDto) {
        this.id = resDto.getId();
        this.title = resDto.getTitle();
        this.content = resDto.getContent();
        this.author = resDto.getAuthor();
        this.createdDate = resDto.getCreatedDate();
        this.modifiedDate = resDto.getModifiedDate();
    }
}
