ALTER TABLE gestores ADD CONSTRAINT fk_gestores_secoes
FOREIGN KEY (id_secao) REFERENCES secoes (id_secao);

ALTER TABLE projetos ADD CONSTRAINT fk_projetos_gestores
FOREIGN KEY (gestor_id) REFERENCES gestores (id_gestor);