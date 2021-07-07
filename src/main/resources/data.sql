INSERT INTO klinika VALUES (10, 'Sabac', 'Klinika', 'Super klinika', '2021-04-01 12:00');
INSERT INTO administrator VALUES (1, 'mail@gmail.com', 'Marko', 'lozinka', 'Markovic', 'KLINICKOG_CENTRA', null);
INSERT INTO zdravstveni_karton VALUES (1);
INSERT INTO pacijent VALUES (1, 'wlqmpek@hotmail.com', 'Milos', '$2a$10$CBz5kLNHcaTHqQzm9iL0FuDXQHVdYm5cQvbZNmWPZ0Hn2bS1OvRTS', 'Vlku', '13121312', '1', 1);
INSERT INTO zdravstveni_karton VALUES (1);
INSERT INTO zdravstveni_karton VALUES (2);
INSERT INTO zdravstveni_karton VALUES (3);

INSERT INTO authority VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO authority VALUES (2, 'ROLE_LEKAR');
INSERT INTO authority VALUES (3, 'ROLE_MEDICINSKA_SESTRA');
INSERT INTO authority VALUES (3, 'ROLE_PACIJENT');

INSERT INTO korisnici_authority VALUES (1, 1);
