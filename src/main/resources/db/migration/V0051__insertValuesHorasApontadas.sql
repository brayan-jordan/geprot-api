INSERT INTO usuarios VALUES (67266,'$2a$10$vAZfFnx9T6IvxlfO9sU4AOQb0qLm/LA10vEY4I2V6CxEkVucuZAHG', '1', 'Daniela', 'daniela@gmail.com', 5.0);

INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR", 67266);

INSERT INTO usuarios VALUES (67267,'$2a$10$vAZfFnx9T6IvxlfO9sU4AOQb0qLm/LA10vEY4I2V6CxEkVucuZAHG', '1', 'Guizada', 'guizada@gmail.com', 2.5);

INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR", 67267);

INSERT INTO consultores_alocados VALUES (null, 1, 67266, 50, 6);

INSERT INTO consultores_alocados VALUES (null, 1, 67267, 50, 6);

INSERT INTO horas_apontadas VALUES (null, 1, 30, 20210209, 67266, "Trabalho legalzin", "PENDENTE");

INSERT INTO horas_apontadas VALUES (null, 1, 30, 20210209, 67267, "Trabalho legalzin", "APROVADO");