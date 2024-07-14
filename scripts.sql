CREATE DATABASE IF NOT EXISTS aeroline;

USE aeroline;

CREATE TABLE IF NOT EXISTS features (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    iconurl VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customers (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	email VARCHAR(255) UNIQUE,
	identitycard VARCHAR(255) UNIQUE NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS flight (
	id INT NOT NULL AUTO_INCREMENT,
	flightNumber VARCHAR(255) UNIQUE NOT NULL,
	departure VARCHAR(255) NOT NULL,
	arrival VARCHAR(255) NOT NULL,
	logoURL varchar(255),
	departureTime DATETIME NOT NULL,
	arrivalTime DATETIME NOT NULL,
	price FLOAT NOT NULL,
	airline VARCHAR(255) NOT NULL,
	duration VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS seats (
	id INT AUTO_INCREMENT PRIMARY KEY,
	flightID INT NOT NULL,
	seatsCODE VARCHAR(5) NOT NULL,
	status BIT default 0,
	FOREIGN KEY (flightID) REFERENCES flight(id)
)


DELIMITER $$

CREATE PROCEDURE insert_flights()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE cityCount INT DEFAULT 20;
    DECLARE airlineCount INT DEFAULT 8;
    DECLARE cities TEXT;
    DECLARE airlines TEXT;
    SET cities = 'New York,Los Angeles,Chicago,Houston,Phoenix,Philadelphia,San Antonio,San Diego,Dallas,San Jose,Austin,Jacksonville,Fort Worth,Columbus,Charlotte,San Francisco,Indianapolis,Seattle,Denver,Washington';
    SET airlines = 'American Airlines,United Airlines,Delta Airlines,Southwest Airlines,JetBlue,Alaska Airlines,Spirit Airlines,Frontier Airlines';
    WHILE i <= 200 DO
        INSERT INTO flight (flightNumber, departure, arrival, logoURL, departureTime, arrivalTime, price, airline, duration)
        VALUES (
            CONCAT('FL', i),
            SUBSTRING_INDEX(SUBSTRING_INDEX(cities, ',', 1 + (i MOD cityCount)), ',', -1),
            SUBSTRING_INDEX(SUBSTRING_INDEX(cities, ',', 1 + ((i + 1) MOD cityCount)), ',', -1),
            CONCAT('https://example.com/logo', 1 + (i MOD 9), '.png'),
            DATE_FORMAT(DATE_ADD('2024-07-01', INTERVAL FLOOR(1 + (RAND() * 29)) DAY) + INTERVAL FLOOR(6 + (RAND() * 12)) HOUR, '%Y-%m-%d %H:%i:%s'),
            DATE_FORMAT(DATE_ADD('2024-07-01', INTERVAL FLOOR(1 + (RAND() * 29)) DAY) + INTERVAL FLOOR(7 + (RAND() * 14)) HOUR, '%Y-%m-%d %H:%i:%s'),
            50 + (i MOD 451),
            SUBSTRING_INDEX(SUBSTRING_INDEX(airlines, ',', 1 + (i MOD airlineCount)), ',', -1),
            CONCAT(1 + (i MOD 12), 'h')
        );
        SET i = i + 1;
    END WHILE;
END$$

DELIMITER ;

CALL insert_flights();


DELIMITER //

CREATE PROCEDURE insert_seats()
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE flight_id INT;
    DECLARE i INT;
    DECLARE seat_code VARCHAR(5);

    -- Declara el cursor para iterar sobre los vuelos
    DECLARE cur CURSOR FOR SELECT id FROM flight;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    -- Abre el cursor
    OPEN cur;

    -- Bucle sobre cada vuelo usando el cursor
    read_loop: LOOP
        FETCH cur INTO flight_id;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Generar 150 asientos para cada vuelo
        SET i = 1;
        WHILE i <= 150 DO
            SET seat_code = CONCAT('S', LPAD(i, 3, '0'));  -- Generar código de asiento
            INSERT INTO seats (flightID, seatsCODE, status)
            VALUES (flight_id, seat_code, 0);
            SET i = i + 1;
        END WHILE;
    END LOOP;

    -- Cierra el cursor
    CLOSE cur;
END //

DELIMITER ;

-- Llamar al procedimiento para insertar los asientos
CALL insert_seats();