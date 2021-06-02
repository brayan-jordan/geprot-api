CREATE TABLE gestor (
  idGestor bigint(20) NOT NULL,
  senha varchar(50) NOT NULL,
  idSecao int(11) NOT NULL,
  nomeSecao varchar(100) DEFAULT NULL,
  nomeGestor varchar(100) NOT NULL,
  email varchar(100) DEFAULT NULL,
  PRIMARY KEY (idGestor)
)