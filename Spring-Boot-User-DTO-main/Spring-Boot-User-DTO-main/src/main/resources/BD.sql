DROP DATABASE IF EXISTS dto_user;
CREATE DATABASE  dto_user;
USE dto_user;
CREATE TABLE Users
( id integer  PRIMARY KEY auto_increment,
  firstName VARCHAR(15),
  lastName VARCHAR(50),
  email VARCHAR(50),
  password VARCHAR(15)

);

INSERT INTO users  VALUES
                      (1, 'juan','alonso','juan@gmail.com', 'juan'),
                      (2, 'ana','gutierrez','ana@gmail.com', 'ana'),
                      (3, 'luis','perez','luis@gmail.com', 'luis'),
                      (4, 'sara','garcia','sara@gmail.com', 'sara'),
                      (5, 'mariano','leal','mariano@gmail.com', 'mariano'),
                      (6, 'angel','labrador','angel@gmail.com', 'angel'),
                      (7, 'lucia','minguez','lucia@gmail.com', 'lucia'),
                      (8, 'javier','bastida','javier@gmail.com', 'javier');