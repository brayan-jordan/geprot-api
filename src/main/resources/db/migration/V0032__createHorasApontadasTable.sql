CREATE TABLE horas_apontadas (
    id bigint not null auto_increment,
    projeto_id int not null,
    quantidade_horas int not null,
    data_hora date not null,
    consultor_id bigint not null,
    primary key (id)
);