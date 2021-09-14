ALTER TABLE projetos
ADD COLUMN nome_solicitante VARCHAR (60) NOT NULL;

ALTER TABLE projetos
ADD COLUMN nome_responsavel VARCHAR (60) NOT NULL;

INSERT INTO consultores_alocados VALUES (null, 1, 67266, 50, 6);

INSERT INTO consultores_alocados VALUES (null, 1, 67267, 50, 6);

INSERT INTO horas_apontadas VALUES (null, 1, 30, 20210209, 67266, "Trabalho legalzin", "PENDENTE");

INSERT INTO horas_apontadas VALUES (null, 1, 30, 20210209, 67267, "Trabalho legalzin", "APROVADO");

UPDATE projetos SET nome_responsavel = "Tathiana e Jonathan" WHERE id = 1;

UPDATE projetos SET nome_solicitante = "Jair e jairo" WHERE id = 1;

