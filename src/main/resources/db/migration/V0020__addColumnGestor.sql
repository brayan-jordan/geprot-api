ALTER TABLE gestores
ADD COLUMN usuario_id bigint NOT NULL DEFAULT 0;

ALTER TABLE gestores
ADD CONSTRAINT fk_usuario_gestores
FOREIGN KEY (usuario_id) REFERENCES usuario(id);