CREATE TABLE resources (
	resource_id INTEGER NOT NULL, 
	name VARCHAR(80) NOT NULL, 
	description VARCHAR(255), 
	owner_external_id VARCHAR(255) NOT NULL, 
	mail VARCHAR(255), 
	PRIMARY KEY (resource_id)
);