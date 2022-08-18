package jp.co.jeus_blog.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@PropertySource("classpath:filepath.property")
@Component
public class FilePathProperty {

    @Value("${image.filepath:")
    private String imageFilePath;
}
