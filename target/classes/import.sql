INSERT INTO Greeting(id, name)
VALUES (nextval('Greeting_SEQ'), 'Alice');
INSERT INTO Greeting(id, name)
VALUES (nextval('Greeting_SEQ'), 'Bob');

INSERT INTO person (id, birth, name, status) VALUES (1, '1995-09-12', 'Emily Brown', 0);
INSERT INTO person (id, birth, name, status) VALUES (2, '1997-11-05', 'John Frusciante', 1);
INSERT INTO person (id, birth, name, status) VALUES (3, '1988-01-23', 'Sophia Turner', 0);
INSERT INTO person (id, birth, name, status) VALUES (4, '1992-04-17', 'Daniel Craig', 1);
INSERT INTO person (id, birth, name, status) VALUES (5, '1985-07-09', 'Olivia Smith', 0);
INSERT INTO person (id, birth, name, status) VALUES (6, '1990-10-30', 'James Bond', 1);
INSERT INTO person (id, birth, name, status) VALUES (7, '1993-02-15', 'Liam Neeson', 0);
INSERT INTO person (id, birth, name, status) VALUES (8, '1987-08-27', 'Ava Green', 1);
INSERT INTO person (id, birth, name, status) VALUES (9, '1991-12-19', 'Mia Wallace', 0);
INSERT INTO person (id, birth, name, status) VALUES (10, '1994-03-22', 'Noah Black', 1);
INSERT INTO person (id, birth, name, status) VALUES (11, '1989-06-10', 'Isabella White', 0);
INSERT INTO person (id, birth, name, status) VALUES (12, '1996-09-28', 'Ethan Hunt', 1);
INSERT INTO person (id, birth, name, status) VALUES (13, '1998-01-03', 'Lucas Scott', 0);
INSERT INTO person (id, birth, name, status) VALUES (14, '1986-11-11', 'Charlotte Hale', 1);
INSERT INTO person (id, birth, name, status) VALUES (15, '1993-05-24', 'Amelia Pond', 0);
INSERT INTO person (id, birth, name, status) VALUES (16, '1990-08-14', 'Henry Morgan', 1);
INSERT INTO person (id, birth, name, status) VALUES (17, '1992-02-05', 'Jack Dawson', 0);
INSERT INTO person (id, birth, name, status) VALUES (18, '1995-07-18', 'Ella Fitzgerald', 1);
INSERT INTO person (id, birth, name, status) VALUES (19, '1984-12-23', 'William Turner', 0);
INSERT INTO person (id, birth, name, status) VALUES (20, '1997-10-06', 'Madison Lee', 1);

ALTER SEQUENCE person_seq RESTART WITH 21;