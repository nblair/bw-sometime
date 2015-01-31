CREATE TABLE resources (
	resource_id INTEGER NOT NULL, 
	name VARCHAR(80) NOT NULL, 
	description VARCHAR(255), 
	owner_id INTEGER NOT NULL, 
	mail VARCHAR(255), 
	PRIMARY KEY (resource_id), 
	CONSTRAINT resources_fk1 FOREIGN KEY (owner_id) REFERENCES OWNERS (internal_id)
);