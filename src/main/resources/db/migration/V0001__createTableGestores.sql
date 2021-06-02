CREATE TABLE gestor (
  idGestor bigint NOT NULL,
  senha varchar(100) NOT NULL,
  idSecao int NOT NULL,
  nomeSecao varchar(100) NOT NULL,
  nomeGestor varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  PRIMARY KEY (idGestor)
)