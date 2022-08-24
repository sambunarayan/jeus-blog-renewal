package jp.co.jeus_blog.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ITPostListDto {

    private int totalPostNum;
    private List<PostResponseDto> posts;

    @Builder
    public ITPostListDto(int totalPostNum, List<PostResponseDto> posts) {
        this.totalPostNum = totalPostNum;
        this.posts = posts;
    }
}
