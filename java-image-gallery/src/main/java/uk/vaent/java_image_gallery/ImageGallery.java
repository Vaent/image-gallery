package uk.vaent.java_image_gallery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.vaent.java_image_gallery.model.DatabaseAccess;
import uk.vaent.java_image_gallery.model.Image;
import uk.vaent.java_image_gallery.model.ImageAndFileDetails;

@Controller
@RequestMapping("/gallery")
public class ImageGallery {

    private final ClassPathResource staticResourcesDirectory = new ClassPathResource("static/");

    @Autowired
    DatabaseAccess databaseAccess;

    @GetMapping
    public String gallery(ModelMap model) {
        try {
            List<ImageAndFileDetails> imageAndFileDetailsList = databaseAccess.getAllBasicImageDetails()
                .stream()
                .map(this::checkImageFile)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            model.addAttribute("imageFileList", imageAndFileDetailsList);
        } catch (SQLException ex) {
            System.out.println(ex);
            model.addAttribute("imageFileList", new ArrayList<ImageAndFileDetails>());
        }
        return "gallery";
    }

    private ImageAndFileDetails checkImageFile(Image image) {
        if (image == null || image.imageName() == null) return null;

        String filename = image.imageName().toLowerCase().replace(" ", "-");
        String filePath = "images/" + filename + "." + image.fileFormat();
        if (staticResourcesDirectory.createRelative(filePath).exists()) {
            return new ImageAndFileDetails(image, filename, filePath);
        }
        return null;
    }

}
