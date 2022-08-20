package jp.co.jeus_blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TimeLineMainPageResponseDto {

    private Integer lastIndexId;
    private String aboutMe;
    private String mainTitle;
    private String mainTitleDetail;
}
