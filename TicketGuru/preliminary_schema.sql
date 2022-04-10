CREATE TABLE role(
roleid bigserial NOT NULL,
description varchar(50),
PRIMARY KEY(roleid) 
);

CREATE TABLE employee(
employeeid bigserial NOT NULL,
firstname varchar(50),
lastname varchar(250),
username varchar(50) UNIQUE NOT NULL,
password varchar(250) UNIQUE NOT NULL,
roleid bigserial,
PRIMARY KEY(employeeid),
CONSTRAINT fk_role
	FOREIGN KEY(roleid)
		REFERENCES role(roleid)
);

CREATE TABLE event(
eventid bigserial NOT NULL,
description varchar(100),
location varchar(50),
city varchar(50),
ticketcount integer,
datetime timestamp,
duration integer,
PRIMARY KEY(eventid)
);

CREATE TABLE sale(
saleid bigserial NOT NULL,
datetime timestamp,
employeeid bigserial,
PRIMARY KEY(saleid), 
CONSTRAINT fk_employee
	FOREIGN KEY(employeeid)
		REFERENCES employee(employeeid)
);

CREATE TABLE tickettype(
tickettypeid bigserial NOT NULL,
name varchar(50),
price decimal,
eventid bigserial,
PRIMARY KEY(tickettypeid),
CONSTRAINT fk_event
	FOREIGN KEY (eventid)
		REFERENCES event (eventid)
);

CREATE TABLE ticket(
ticketid bigserial NOT NULL,
valid 	boolean,
ticketprice decimal,
eventid bigserial,
saleid bigserial,
tickettypeid bigserial,
PRIMARY KEY(ticketid),
CONSTRAINT fk_event
	FOREIGN KEY(eventid)
		REFERENCES event(eventid),
CONSTRAINT fk_sale
	FOREIGN KEY(saleid)
		REFERENCES sale(saleid),
CONSTRAINT fk_tickettype
	FOREIGN KEY(tickettypeid)
		REFERENCES tickettype(tickettypeid)
);

INSERT INTO role(description)
VALUES('USER'), 
      ('ADMIN');

INSERT INTO employee(firstname, lastname, username, hashpassword, roleid)
VALUES('Liisa', 'Ihmemaa', 'user', '$2a$10$IBAaLtmae8y7aB2NhXiOLuZasieKoum4sLznAJGAq96eSblHra/pi', 1), 
      ('Jaakko', 'Varpunen', 'admin', '$2a$10$gob7SitQ7d5cA8pm2wChsOypL55vLrrgIHrIHj4Yd49/EpjrxPZs6', 2);

INSERT INTO sale(datetime, employeeid)
VALUES('2022-02-17 13:04:00', 1),
      ('2022-07-22 20:45:00', 2);

INSERT INTO event(description, location, city, ticketcount, datetime, duration)
VALUES('YLL:n kevätkonsertti', 'Finlandia-Talo', 'Helsinki', 400, '2023-05-01 18:00:00', 90),
      ('Matteus-passio', 'Johanneksen kirkko', 'Helsinki', 300, '2023-04-14 19:00:00', 120),
      ('Sinfoniaorkesteri',  'Korundi', 'Rovaniemi', 300, '2023-06-22 18:00:00', 100);

INSERT INTO tickettype(name, price, eventid)
VALUES('Adult', 49.9, 1),
      ('Child', 29.9, 1),
      ('Adult', 10.0, 2),
      ('Child', 5.0, 2);

INSERT INTO ticket(valid, ticketprice, eventid, saleid, tickettypeid)
VALUES(true, 49.9, 1, 1, 1),
      (true, 10.0, 2, 2, 2);