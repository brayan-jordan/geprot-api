CREATE TABLE consultores_alocados (
    id bigint not null,
    projetos_id bigint not null,
    usuarios_id bigint not null,
    primary key (id)
);