CREATE TABLE image_details (
	id SERIAL PRIMARY KEY,
	image_name VARCHAR,
	image_description VARCHAR,
	file_format VARCHAR(4),
    date_created DATE
);

INSERT INTO image_details(image_name, image_description, file_format, date_created)
VALUES ('image1', 'HD proportioned image', 'png', '2020-01-01'),
    ('image2', 'nonstandard landscape format', 'png', '2010-01-01'),
    ('image3', 'rough square', 'png', '2020-07-31'),
    ('image4', 'portrait format', 'png', '2018-12-31');
