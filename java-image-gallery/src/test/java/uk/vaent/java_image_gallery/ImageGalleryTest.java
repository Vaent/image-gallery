package uk.vaent.java_image_gallery;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class ImageGalleryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void retrieveGalleryHomePage() throws Exception {
        this.mockMvc.perform(get("/gallery")).andExpect(status().isOk());
    }

    @Test
    public void testNonexistentRoutes() throws Exception {
        this.mockMvc.perform(post("/gallery")).andExpect(status().isMethodNotAllowed());
        this.mockMvc.perform(get("/foo")).andExpect(status().isNotFound());
        this.mockMvc.perform(get("/gallery/foo")).andExpect(status().isNotFound());
    }

}