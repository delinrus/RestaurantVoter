DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS menu_menu_item;
DROP TABLE IF EXISTS menu_items;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE global_seq AS BIGINT START WITH 100000;

CREATE TABLE users (
   id 				BIGINT GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
   email 			VARCHAR(255)				NOT NULL,
   password 		VARCHAR(255) 				NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx
    ON users (email);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
    id   	BIGINT GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name 	VARCHAR(70)		NOT NULL
);

CREATE UNIQUE INDEX restaurants_unique_name_idx
    ON restaurants (name);

CREATE TABLE menus (
   id               BIGINT GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
   date             DATE       NOT NULL,
   restaurant_id    BIGINT			NOT NULL,
   FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
) ;

CREATE UNIQUE INDEX menus_unique_restaurant_date_idx
    ON menus (restaurant_id, date);

CREATE TABLE menu_items (
    id              BIGINT GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    restaurant_id 	BIGINT			NOT NULL,
    name			VARCHAR(255)    NOT NULL,
    price			BIGINT			NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE menu_menu_item
(
    menu_id BIGINT NOT NULL,
    menu_item_id BIGINT NOT NULL,
    PRIMARY KEY (menu_id, menu_item_id),

    CONSTRAINT menu_menu_item_fk1 FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT menu_menu_item_fk2 FOREIGN KEY (menu_item_id) REFERENCES menu_items (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE votes (
   id                   BIGINT GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
   user_id 			    BIGINT			NOT NULL,
   restaurant_id 		BIGINT			NOT NULL,
   date 			    DATE		NOT NULL,
   FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
   FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX votes_unique_user_date_idx
    ON votes (user_id, date);

CREATE INDEX votes_date_idx
    ON votes (date);