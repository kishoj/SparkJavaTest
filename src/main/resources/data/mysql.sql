--mysql -u root -p
--root

CREATE DATABASE testdb;

USE testdb;

CREATE TABLE user_phone
(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL, 
	last_name VARCHAR(20) NOT NULL, 
	phone VARCHAR(40) NOT NULL
);

INSERT INTO user_phone (first_name, last_name, phone) VALUES ('Kishoj', 'Bajracharya', '+66-875973739');
INSERT INTO user_phone (first_name, last_name, phone) VALUES ('Prasanna', 'Bajracharya', '+977-6612416');
INSERT INTO user_phone (first_name, last_name, phone) VALUES ('Ram', 'Kasula', '+977-6614443');
INSERT INTO user_phone (first_name, last_name, phone) VALUES ('Suraj', 'Maharjan', '+977-4217330');