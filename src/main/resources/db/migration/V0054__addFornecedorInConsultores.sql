CREATE TABLE fornecedores (
    id bigint not null,
    nome varchar(100) not null,
    primary key (id)
);

INSERT INTO fornecedores VALUES
    (1, "Sem fornecedor"),
    (2, "Daniela desenhos"),
    (3, "Fornecedor de peças"),
    (4, "Senai empreendimentos");

ALTER TABLE usuarios ADD COLUMN
fornecedores_id BIGINT NOT NULL DEFAULT 1;

ALTER TABLE usuarios ADD CONSTRAINT fk_fornecedores_id
FOREIGN KEY (fornecedores_id) REFERENCES fornecedores(id);