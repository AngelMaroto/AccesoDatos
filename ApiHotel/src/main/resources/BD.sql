DROP DATABASE IF EXISTS apiHotel;
CREATE DATABASE apiHotel;
USE apiHotel;

CREATE TABLE HOTEL(
                    idhotel int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(50) DEFAULT NULL,
                    descripcion VARCHAR(256) DEFAULT NULL,
                    piscina boolean,
                    categoria int,
                    localidad VARCHAR(100) DEFAULT NULL

) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

INSERT INTO HOTEL (nombre, descripcion, piscina, categoria, localidad)
VALUES ('Juan de Austria', 'Elegante hotel situado frente al Campo Grande.', false, 4, 'Valladolid');

INSERT INTO HOTEL (nombre, descripcion, piscina, categoria, localidad)
VALUES ('Grand Marina Resort', 'Exclusivo hotel de 5 estrellas con vistas al mar.', true, 5, 'Barcelona');

INSERT INTO HOTEL (nombre, descripcion, piscina, categoria, localidad)
VALUES ('Hostal El Sol', 'Alojamiento céntrico, limpio y económico.', false, 2, 'Madrid');

INSERT INTO HOTEL (nombre, descripcion, piscina, categoria, localidad)
VALUES ('Tech Plaza', 'Hotel moderno en el distrito financiero.', true, 3, 'Valencia');

CREATE TABLE HABITACION(
                        idhabitacion int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        tamano VARCHAR(30) DEFAULT NULL,
                        personas int,
                        precio_noche DECIMAL(10,2),
                        desayuno boolean,
                        ocupada boolean,
                        idhotel int not null,
                        FOREIGN KEY (idhotel) REFERENCES Hotel(idhotel)


) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

INSERT INTO HABITACION (tamano, personas, precio_noche, desayuno, ocupada, idhotel)
VALUES
    ('Doble Estándar', 2, 85.50, true, false, 1),
    ('Suite Junior', 2, 120.00, true, true, 1);

INSERT INTO HABITACION (tamano, personas, precio_noche, desayuno, ocupada, idhotel)
VALUES
    ('Suite Presidencial', 2, 450.00, true, false, 2),
    ('Suite Individual', 1, 180.00, false, false, 2);

INSERT INTO HABITACION (tamano, personas, precio_noche, desayuno, ocupada, idhotel)
VALUES
    ('Individual', 1, 35.00, false, true, 3),
    ('Doble Pequeña', 2, 50.00, false, false, 3);

INSERT INTO HABITACION (tamano, personas, precio_noche, desayuno, ocupada, idhotel)
VALUES
    ('Ejecutiva', 1, 95.00, true, false, 4);