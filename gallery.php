<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title>Gallery</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div class="image-group">
            <?php
                $filenames = array("image1.png", "image2.png", "image3.png", "image4.png");
                foreach ($filenames as $filename) {
                    echo <<<THUMB
                    <span class="thumbnail">
                        <img src="images/$filename">
                    </span>
                    THUMB;
                }
            ?>
        </div>
    </body>
</html>
