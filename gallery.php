<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title>Gallery</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div id="gallery-settings">
            <p>Show older content?
                <span class="toggle-selector">
                    <span class="toggle-not-selected" data-toggle-position="0" onclick="toggle(this)">No </span><!--
                 --><span class="toggle-slider" data-currently-selected="1" onclick="toggle(this)"></span><!--
                 --><span class="toggle-currently-selected" data-toggle-position="1"> Yes</span>
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
                            <img src="images/$filename" alt="{$image['image_description']}">
                        </span>
                        THUMB;
                    }
                }
            ?>
        </div>

        <script>
            function toggle(selector) {
                if (selector.classList.contains("toggle-slider") || selector.classList.contains("toggle-not-selected")) {
                    siblings = Array.from(selector.parentElement.children);
                    slider = siblings.find(s => s.classList.contains("toggle-slider"));
                    startPos = slider.dataset.currentlySelected;
                    endPos = 1 ^ startPos;
                    slider.dataset.currentlySelected = endPos;
                    wasActive = siblings.find(s => s.dataset.togglePosition == startPos);
                    wasActive.classList.replace("toggle-currently-selected", "toggle-not-selected");
                    wasActive.onclick = function() {toggle(this)};
                    wasInactive = siblings.find(s => s.dataset.togglePosition == endPos);
                    wasInactive.classList.replace("toggle-not-selected", "toggle-currently-selected");
                    wasInactive.onclick = null;
                }
            }
        </script>
    </body>
</html>
