package uk.vaent.java_image_gallery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gallery")
public class ImageGallery {

    @GetMapping
    public String gallery() {
        return "gallery";
    }

}
