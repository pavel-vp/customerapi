DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
id BIGINT NOT NULL AUTO_INCREMENT,
name varchar(255),
status varchar(255),
createTime BIGINT,
contactInfo varchar(255),
primary key (id)
);

insert into customers (id, name, status, createTime, contactInfo) values (1, 'Bob Dilan', 'CURRENT', ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'New York');
insert into customers (id, name, status, createTime, contactInfo) values (2, 'John Doe', 'NONACTIVE', ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'New York');
insert into customers (id, name, status, createTime, contactInfo) values (3, 'Michael Jackson', 'PROSPECTIVE', ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Austin');
insert into customers (id, name, status, createTime, contactInfo) values (4, 'Rob Stewart', 'CURRENT', ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Berlin');
insert into customers (id, name, status, createTime, contactInfo) values (5, 'Ray Liotta', 'CURRENT', ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Prague');


DROP TABLE IF EXISTS notes;

CREATE TABLE notes (
id   BIGINT NOT NULL AUTO_INCREMENT,
customerId  BIGINT,
createTime BIGINT,
data varchar(255),
primary key (id)
);

insert into notes (id, customerId, createTime, data) values (1, 1, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Concert in Rome');
insert into notes (id, customerId, createTime, data) values (2, 1, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Concert in Milan');

insert into notes (id, customerId, createTime, data) values (3, 2, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Very old client');

insert into notes (id, customerId, createTime, data) values (4, 3, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Try reaching out');
insert into notes (id, customerId, createTime, data) values (5, 3, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Second try');
insert into notes (id, customerId, createTime, data) values (6, 3, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Not responding');

insert into notes (id, customerId, createTime, data) values (7, 4, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Concert in New York');
insert into notes (id, customerId, createTime, data) values (8, 4, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Try get to Toronto');

insert into notes (id, customerId, createTime, data) values (9, 5, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), 'Not responding');
