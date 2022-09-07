package jp.co.jeus_blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ITBoardRegisterFormDto {

    private String boardName;
    private String category;
    private String colorSelect;
    private String detailsArea;
}
