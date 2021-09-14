ALTER TABLE secoes ADD COLUMN
nome_responsavel VARCHAR (60) NOT NULL;

ALTER TABLE secoes ADD CONSTRAINT fk_nome_responsavel
FOREIGN KEY (nome_responsavel) REFERENCES usuarios(nome);