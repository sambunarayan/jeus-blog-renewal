package jp.co.jeus_blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ITPostRegisterFormResponseDto {

    private Long id;
    private String boardName;
    private String title;
    private String content;
    private String author;
    private String currentPage;

    public ITPostRegisterFormResponseDto(PostResponseDto post) {
        this.id = post.getId();
        this.boardName = post.getBoardName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
    }
}
