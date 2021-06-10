ALTER TABLE gestores ADD CONSTRAINT fk_gestores_secoes
FOREIGN KEY (idsecao) REFERENCES secoes (idsecao);

ALTER TABLE projetos ADD CONSTRAINT fk_projetos_gestores
FOREIGN KEY (gestorid) REFERENCES gestores (idgestor);