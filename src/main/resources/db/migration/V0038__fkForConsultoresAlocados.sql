ALTER TABLE consultores_alocados ADD CONSTRAINT fk_projetos_alocados
FOREIGN KEY (projetos_id) REFERENCES projetos(id);

ALTER TABLE consultores_alocados ADD CONSTRAINT fk_usuarios_alocados
FOREIGN KEY (usuarios_id) REFERENCES usuarios(id);