create table projetos (
    id_projeto int NOT NULL auto_increment,
    nome_projeto varchar(100) NOT NULL,
    data_inicio date NOT NULL,
    data_finalizacao date,
    horas_previstas int NOT NULL,
    horas_trabalhadas int,
    valor_projeto double(20,2) not null,
    valor_utilizado double(20,2),
    valor_restante double(20,2) not null,
    gestor_id bigint not null,
    status_projeto varchar(20) not null,
    PRIMARY KEY(id_projeto)
);