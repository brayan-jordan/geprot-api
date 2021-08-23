ALTER TABLE usuarios ADD CONSTRAINT fk_usuarios_secoes
FOREIGN KEY (secao_id) REFERENCES secoes (id);

ALTER TABLE projetos ADD CONSTRAINT fk_projetos_gestores
FOREIGN KEY (usuario_id) REFERENCES usuarios (id);