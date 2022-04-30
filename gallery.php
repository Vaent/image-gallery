<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title>Gallery</title>
        <link rel="stylesheet" href="css/styles.css">
        <script src="scripts/toggles.js"></script>
    </head>
    <body>
        <div id="gallery-settings">
            <p>Show older content?
                <span class="toggle-selector">
                    <span class="toggle-not-selected" data-toggle-position="0" onclick="toggleOlderContent(this)">No</span><!--
                    Line break between spans is commented to ensure continuity of clickable area (avoid insertion of non-clickable whitespace)
                 --><span class="toggle-slider" data-currently-selected="1" onclick="toggleOlderContent(this)"></span><!--
                 --><span class="toggle-currently-selected" data-toggle-position="1">Yes</span>
                </span>
            </p>
        </div>
        <div class="image-group">
            <?php
                $config = parse_ini_file('database/db_config.ini');
                try {
                    $pg = new PDO("pgsql:host={$config['host']};port={$config['port']};dbname={$config['dbname']}", $config['username'], $config['password']);
                    $images = $pg->query('SELECT * FROM get_all_basic_image_details();');
                } catch(Exception $ex) {
                    die('Unable to display images (could not retrieve details from the database)');
                }

                foreach ($images as $image) {
                    $filename = "{$image['image_name']}.{$image['file_format']}";
                    if (file_exists("images/$filename")) {
                        echo <<<THUMB
                        <span class="thumbnail">
                            <img src="images/$filename" alt="{$image['image_description']}" data-date-created="{$image['date_created']}" onclick="expandImage(this)">
                        </span>
                        THUMB;
                    }
                }
            ?>
        </div>

        <div id="expanded-image-overlay" onclick="collapseImage()">
            <img id="expanded-image">
        </div>
        <script src="scripts/overlay.js"></script>
    </body>
</html>
