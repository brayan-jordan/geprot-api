CREATE TABLE gestores (
  id_gestor bigint NOT NULL,
  senha varchar(100) NOT NULL,
  id_secao int NOT NULL,
  nome_secao varchar(100) NOT NULL,
  nome_gestor varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  PRIMARY KEY (id_gestor)
)