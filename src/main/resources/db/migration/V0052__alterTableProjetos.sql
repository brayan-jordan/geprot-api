ALTER TABLE projetos
ADD COLUMN nome_solicitante VARCHAR (60) NOT NULL;

ALTER TABLE projetos
ADD COLUMN nome_responsavel VARCHAR (60) NOT NULL;

INSERT INTO consultores_alocados VALUES (null, 1, 67266, 50, 6);

INSERT INTO consultores_alocados VALUES (null, 1, 67267, 50, 6);

INSERT INTO horas_apontadas VALUES (null, 1, 4, 20210209, 67266, "Refatoração do back end, visando melhorar o código", "PENDENTE");

INSERT INTO horas_apontadas VALUES (null, 1, 30, 20210209, 67267, "Aplicação dos conceitos do clean code, visando melhoria no código", "APROVADO");

INSERT INTO horas_apontadas VALUES (null, 1, 30, 20210209, 67267, "Remoção do bug que estava sendo causado na hora de cadastrar um projeto com o ID nulo", "APROVADO");

INSERT INTO horas_apontadas VALUES (null, 1, 4, 20210209, 67266, "Adição da opção do cadastro de projetos, teste", "PENDENTE");

UPDATE projetos SET nome_responsavel = "Tathiana e Jonathan" WHERE id = 1;

UPDATE projetos SET nome_solicitante = "Jair e jairo" WHERE id = 1;

