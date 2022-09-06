package jp.co.jeus_blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ImageUploadResponseDto implements Serializable {

    private String imageName;
}
