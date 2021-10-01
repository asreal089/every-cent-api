CREATE TABLE User(
	USER_ID bigint PRIMARY KEY,
	PROVIDER_USER_ID varchar(255),
	email varchar(255),
	enabled boolean,
	DISPLAY_NAME varchar(255),
	created_date timestamp,
	modified_date timestamp,
	password varchar(255),
	provider varchar(255)
);

CREATE TABLE Role(
	ROLE_ID int PRIMARY KEY,
	name varchar(50)
);

CREATE TABLE User_Role(
	USER_ID bigint,
	ROLE_ID int,
	FOREIGN KEY(USER_ID) REFERENCES User(USER_ID),
	FOREIGN KEY(ROLE_ID) REFERENCES Role(ROLE_ID),
	PRIMARY KEY(USER_ID, ROLE_ID)
);

INSERT INTO Role(ROLE_ID, name) VALUES(1, 'USER');
INSERT INTO Role(ROLE_ID, name) VALUES(2, 'ROLE_USER');
INSERT INTO Role(ROLE_ID, name) VALUES(3, 'ROLE_ADMIN');
INSERT INTO Role(ROLE_ID, name) VALUES(4, 'ROLE_MODERATOR');