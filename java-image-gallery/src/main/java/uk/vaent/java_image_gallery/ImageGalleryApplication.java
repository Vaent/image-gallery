package uk.vaent.java_image_gallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:db_config.properties")
public class ImageGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageGalleryApplication.class, args);
	}

}
