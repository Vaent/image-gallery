# image-gallery

A generic/template web page for displaying pictures.

Three versions are provided:

- [gallery.html](./gallery.html) embeds all images, with metadata, in a static page;
- [gallery.php](./gallery.php) includes all functionality of the HTML page, and fetches image details from a PostgreSQL database to dynamically populate the page;
- java-image-gallery contains source code for a Java server based on the PHP template.

The PHP is considered the "primary" version and is used to develop and test new features for the [vaent.uk gallery](https://vaent.uk/gallery).
