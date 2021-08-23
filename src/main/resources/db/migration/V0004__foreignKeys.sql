ALTER TABLE gestores ADD CONSTRAINT fk_gestores_secoes
FOREIGN KEY (secao_id) REFERENCES secoes (id);

ALTER TABLE projetos ADD CONSTRAINT fk_projetos_gestores
FOREIGN KEY (gestor_id) REFERENCES gestores (id);