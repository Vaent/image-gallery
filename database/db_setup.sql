CREATE TABLE image_details (
	id SERIAL PRIMARY KEY,
	image_name VARCHAR,
	image_description VARCHAR,
	file_format VARCHAR(4)
);

INSERT INTO image_details(image_name, image_description, file_format)
VALUES ('image1', 'HD proportioned image', 'png'),
    ('image2', 'nonstandard landscape format', 'png'),
    ('image3', 'rough square', 'png'),
    ('image4', 'portrait format', 'png');
