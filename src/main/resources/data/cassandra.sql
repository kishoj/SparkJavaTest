create keyspace cassandratest with replication = {'class':'SimpleStrategy','replication_factor' : 1};
USE cassandratest;
CREATE TABLE users (email TEXT PRIMARY KEY, first_name TEXT, last_name TEXT);
INSERT INTO users (email, first_name, last_name) VALUES ('kishoj@gmail.com', 'Kishoj', 'Bajracharya');
INSERT INTO users (email, first_name, last_name) VALUES ('prasanna@gmail.com', 'Prasanna', 'Bajracharya');
INSERT INTO users (email, first_name, last_name) VALUES ('ram@gmail.com', 'Ram', 'Kasula');
INSERT INTO users (email, first_name, last_name) VALUES ('suraj@gmail.com', 'Suraj', 'Maharjan');