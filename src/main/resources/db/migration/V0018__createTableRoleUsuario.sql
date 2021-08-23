CREATE TABLE role_usuarios (
id bigint primary key auto_increment,
nome_role varchar(50) not null,
id_usuarios bigint not null
);

ALTER TABLE role_usuarios ADD CONSTRAINT fk_role_usuarios
FOREIGN KEY (id_usuarios) REFERENCES usuarios(id);