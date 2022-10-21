package uk.vaent.java_image_gallery.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseAccess {

    @Value("jdbc:postgresql://${dbhost}:${dbport}/${dbname}")
    private String dbUrl;

    @Value("${dbusername}")
    private String dbUsername;

    @Value("${dbpassword}")
    private String dbPassword;

    public List<Image> getAllBasicImageDetails() throws SQLException {
        ArrayList<Image> images = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement statement = conn.createStatement();
            if (statement.execute("SELECT * FROM get_all_basic_image_details();")) {
                ResultSet results = statement.getResultSet();
                while (results.next()) {
                    images.add(new Image(
                        results.getString("image_name"),
                        results.getString("image_description"),
                        results.getString("file_format"),
                        results.getDate("date_created")
                    ));
                }
            }
            return images;
        }
    }

}
