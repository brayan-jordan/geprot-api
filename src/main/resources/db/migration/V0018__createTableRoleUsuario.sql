CREATE TABLE role_usuarios (
id bigint primary key auto_increment,
nome_role varchar(50) not null,
cadastro bigint not null
);

--INSERT INTO role_usuarios VALUES(null,"ROLE_GESTOR",67264);
--INSERT INTO role_usuarios VALUES(null,"ROLE_GESTOR",67265);

ALTER TABLE role_usuarios
ADD CONSTRAINT fk_role_usuarios
FOREIGN KEY (cadastro) REFERENCES gestores(id);