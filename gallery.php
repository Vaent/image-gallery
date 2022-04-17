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
                $config = parse_ini_file('database/db_config.ini');
                try {
                    $pg = new PDO("pgsql:host={$config['host']};port={$config['port']};dbname={$config['dbname']}", $config['username'], $config['password']);
                    $images = $pg->query('SELECT image_name, image_description, file_format FROM image_details');
                } catch(Exception $ex) {
                    die('Unable to display images (could not retrieve details from the database)');
                }

                foreach ($images as $image) {
                    $filename = "{$image['image_name']}.{$image['file_format']}";
                    if (file_exists("images/$filename")) {
                        echo <<<THUMB
                        <span class="thumbnail">
                            <img src="images/$filename" alt="{$image['image_description']}">
                        </span>
                        THUMB;
                    }
                }
            ?>
        </div>
    </body>
</html>
