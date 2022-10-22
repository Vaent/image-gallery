package uk.vaent.java_image_gallery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.vaent.java_image_gallery.model.DatabaseAccess;
import uk.vaent.java_image_gallery.model.Image;
import uk.vaent.java_image_gallery.model.ImageAndFileDetails;

@Controller
@RequestMapping("/gallery")
public class ImageGallery {

    @Autowired
    DatabaseAccess databaseAccess;

    @GetMapping
    public String gallery(ModelMap model) {
        try {
            List<Image> imageDetails = databaseAccess.getAllBasicImageDetails();
            List<ImageAndFileDetails> imageAndFileDetailsList = new ArrayList<>();
            for (Image image : imageDetails) {
                String filename = image.imageName().toLowerCase().replace(" ", "-");
                String filePath = "images/" + filename + "." + image.fileFormat();
                if (new ClassPathResource("static/" + filePath).exists()) {
                    imageAndFileDetailsList.add(new ImageAndFileDetails(image, filename, filePath));
                }
            }
            model.addAttribute("imageFileList", imageAndFileDetailsList);
        } catch (SQLException ex) {
            System.out.println(ex);
            model.addAttribute("imageFileList", new ArrayList<ImageAndFileDetails>());
        }
        return "gallery";
    }

}
