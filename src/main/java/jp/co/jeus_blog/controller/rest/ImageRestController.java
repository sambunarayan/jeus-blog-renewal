package jp.co.jeus_blog.controller.rest;

import jp.co.jeus_blog.dto.ImageUploadResponseDto;
import jp.co.jeus_blog.service.ImageUploadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RequestMapping("blog-rest/image")
@RestController
public class ImageRestController {

    @Autowired
    private ImageUploadService imageUploadService;

    /**
     * Upload image file.
     *
     * @param file MultipartFile
     * @return ImageResponseDto
     */
    @RequestMapping(value="/upload", method= RequestMethod.POST, produces="application/json")
    @ResponseBody
    public ImageUploadResponseDto upload(MultipartFile file) {
        log.debug(file.getOriginalFilename());
        return new ImageUploadResponseDto(imageUploadService.uploadImage(file));
    }
}
