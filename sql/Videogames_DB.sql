/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Cesar Himura
 * Created: 24 abr 2023
 */
DROP DATABASE videogamesdb;
DROP USER adminvg;

CREATE USER adminvg WITH password 'admin123';
CREATE DATABASE videogamesdb WITH template = template0 owner = adminvg;
\connect videogamesdb;

ALTER DEFAULT PRIVILEGES GRANT ALL ON tables TO adminvg;
ALTER DEFAULT PRIVILEGES GRANT ALL ON sequences TO adminvg;

CREATE TABLE tb_users (
    id INTEGER PRIMARY KEY NOT NULL,
    username VARCHAR(100),
    password VARCHAR(255)
);

CREATE TABLE cat_platforms (
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(100),
    owner VARCHAR(100)
);

CREATE TABLE tb_videogames (
    id INTEGER PRIMARY KEY NOT NULL,
    title VARCHAR(150) NOT NULL,
    id_platform INTEGER NOT NULL,
    physics_format NUMERIC(1) NOT NULL,
    digital_format NUMERIC(1) NOT NULL,
    registered_by INTEGER,
    created_at TIMESTAMP NOT NULL
);

ALTER TABLE tb_videogames ADD CONSTRAINT fk_videogames_platforms
FOREIGN KEY (id_platform) REFERENCES cat_platforms(id);

ALTER TABLE tb_videogames ADD CONSTRAINT fk_videogames_user
FOREIGN KEY (registed_by) REFERENCES tb_users(id);

CREATE SEQUENCE seq_cat_platforms INCREMENT 1 START 1;
CREATE SEQUENCE seq_tb_videogames INCREMENT 1 START 1;
CREATE SEQUENCE seq_tb_users INCREMENT 1 START 1;