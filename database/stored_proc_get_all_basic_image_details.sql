CREATE FUNCTION get_all_basic_image_details(
	OUT image_name VARCHAR,
	OUT image_description VARCHAR,
	OUT file_format VARCHAR(4),
	OUT date_created DATE
)
RETURNS SETOF record
LANGUAGE SQL
AS $PROC$
	SELECT image_name, image_description, file_format, date_created FROM image_details
    ORDER BY date_created DESC;
$PROC$
