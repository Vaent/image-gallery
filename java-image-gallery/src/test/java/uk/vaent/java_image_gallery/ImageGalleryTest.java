package uk.vaent.java_image_gallery;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.vaent.java_image_gallery.model.DatabaseAccess;
import uk.vaent.java_image_gallery.model.Image;

@WebMvcTest
class ImageGalleryTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    ImageGallery imageGallery;

    @MockBean
    DatabaseAccess databaseAccess;

    @Test
    public void retrieveGalleryHomePage() throws Exception {
        Mockito.when(databaseAccess.getAllBasicImageDetails())
            .thenReturn(Arrays.asList(new Image("Image 1", "HD proportioned image", "png", new Date(0))));
        this.mockMvc.perform(get("/gallery"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("<img src=\"images/image-1.png\" alt=\"HD proportioned image\" onclick=\"updateHash(&quot;image-1&quot;)\">")));
    }

    @Test
    public void testNonexistentRoutes() throws Exception {
        this.mockMvc.perform(post("/gallery")).andExpect(status().isMethodNotAllowed());
        this.mockMvc.perform(get("/foo")).andExpect(status().isNotFound());
        this.mockMvc.perform(get("/gallery/foo")).andExpect(status().isNotFound());
    }

}