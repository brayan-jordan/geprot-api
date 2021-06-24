CREATE TABLE ccpagantes (
    codigo bigint not null auto_increment,
    centrodecusto_codigo bigint not null,
    projetos_idprojeto bigint not null,
    primary key (codigo)
);