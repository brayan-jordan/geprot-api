CREATE TABLE usuarios (
  id bigint NOT NULL,
  senha varchar(100) NOT NULL,
  secao_id int NOT NULL,
  nome varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  PRIMARY KEY (id)
);