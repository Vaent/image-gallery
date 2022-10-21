package uk.vaent.java_image_gallery.model;

import java.sql.Date;

public record Image(String imageName, String imageDescription, String fileFormat, Date dateCreated) { }
