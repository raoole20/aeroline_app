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
