package jp.co.jeus_blog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ITPostRegisterRequestDto {

    private Long id;
    private String title;
    private String content;
}
