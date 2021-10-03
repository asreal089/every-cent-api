CREATE TABLE "user"(
	USER_ID BIGSERIAL PRIMARY KEY,
	PROVIDER_USER_ID varchar(255),
	email varchar(255),
	enabled boolean,
	DISPLAY_NAME varchar(255),
	created_date timestamp,
	modified_date timestamp,
	password varchar(255),
	provider varchar(255)
);

CREATE TABLE "role"(
	ROLE_ID SERIAL PRIMARY KEY,
	name varchar(50)
);

CREATE TABLE user_role(
	USER_ID bigint,
	ROLE_ID int,
	FOREIGN KEY(USER_ID) REFERENCES "user"(USER_ID),
	FOREIGN KEY(ROLE_ID) REFERENCES "role"(ROLE_ID),
	PRIMARY KEY(USER_ID, ROLE_ID)
);

INSERT INTO role VALUES(1, 'USER');
INSERT INTO role VALUES(2, 'ROLE_USER');
INSERT INTO role VALUES(3, 'ROLE_ADMIN');
INSERT INTO role VALUES(4, 'ROLE_MODERATOR');