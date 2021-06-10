CREATE TABLE gestores (
  idgestor bigint NOT NULL,
  senha varchar(100) NOT NULL,
  idsecao int NOT NULL,
  nomegestor varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  PRIMARY KEY (idgestor)
);