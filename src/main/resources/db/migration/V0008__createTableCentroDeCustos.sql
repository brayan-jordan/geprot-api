CREATE TABLE ccpagantes (
    idcentrodecusto bigint not null auto_increment,
    nomecentrodecusto varchar(100) not null,
    projeto_idprojeto long not null,
    primary key (idcentrodecusto)
);

ALTER TABLE ccpagantes ADD CONSTRAINT fk_ccpagantes_projetos
FOREIGN KEY (projeto_idprojeto) REFERENCES projetos (idprojeto);