ALTER TABLE role_usuarios
ADD COLUMN usuarios_id BIGINT NOT NULL;

ALTER TABLE role_usuarios
DROP COLUMN cadastro;

ALTER TABLE role_usuarios ADD CONSTRAINT fk_role_usuarios
FOREIGN KEY (usuarios_id) REFERENCES usuarios (id);
