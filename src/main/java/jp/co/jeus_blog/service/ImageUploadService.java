package jp.co.jeus_blog.service;

import jp.co.jeus_blog.constant.FileType;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Image upload service
 */
@Log4j2
@PropertySource("classpath:filepath.property")
@Service
public class ImageUploadService {

    @Value("${image.filepath:}")
    private String imagePath;
    @Value("${logo.image.filepath:}")
    private String logoPath;
    @Value("${logo.image.default}")
    private String defaultLogoImageName;

    /**
     * Upload logoImage
     *
     * @param logoFile
     * @return
     */
    public String uploadLogoImage(MultipartFile logoFile) {
        if (logoFile == null) {
            return defaultLogoImageName;
        }
        return saveFile(logoFile, FileType.LOGO_IMAGE);
    }

    /**
     * Upload image file
     *
     * @param imageFile
     * @return
     */
    public String uploadImage(MultipartFile imageFile) {
        if (imageFile == null) {
            return null;
        }
        return saveFile(imageFile, FileType.IMAGE);
    }

    /**
     * Save image file
     *
     * @param file MultipartFile
     * @param fileType File type
     * @return
     */
    private String saveFile(MultipartFile file, FileType fileType) {
        UUID fileName = UUID.randomUUID();
        log.debug("original file name : " + file.getOriginalFilename());
        log.debug("fileSize : " + file.getSize());
        log.debug("New file name : " + fileName.toString());

        File saveFile = new File(getPath(fileType), fileName.toString());
        if (fileType == FileType.IMAGE) {
            try {
                file.transferTo(saveFile);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        } else if (fileType == FileType.LOGO_IMAGE) {
            try (FileOutputStream thumbnail = new FileOutputStream(saveFile);) {
                Thumbnailator.createThumbnail(file.getInputStream(), thumbnail, 250, 250);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return fileName.toString();
    }

    /**
     * get image path
     *
     * @param fileType
     * @return
     */
    private String getPath(FileType fileType) {
        switch (fileType) {
            case IMAGE:
                return imagePath;
            case LOGO_IMAGE:
                return logoPath;
        }
        return null;
    }
}