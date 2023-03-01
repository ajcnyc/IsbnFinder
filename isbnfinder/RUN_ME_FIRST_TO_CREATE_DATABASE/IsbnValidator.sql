CREATE DATABASE isbn_validator;
USE isbn_validator;

CREATE TABLE isbn_requests(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	isbn VARCHAR(255) NOT NULL,
    times_searched INT NOT NULL,
    UNIQUE KEY isbn_unique(isbn)
);

SELECT * FROM isbn_validator.isbn_requests;