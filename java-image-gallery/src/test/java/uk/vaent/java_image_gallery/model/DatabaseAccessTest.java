package uk.vaent.java_image_gallery.model;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatabaseAccessTest {

    @Autowired
    DatabaseAccess databaseAccess;

    @Test
    void testGetAllBasicImageDetails() throws SQLException {
        assertNotNull(databaseAccess.getAllBasicImageDetails());
    }

}