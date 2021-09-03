CREATE TABLE horas_apontadas (
    id bigint not null auto_increment,
    projetos_id int not null,
    quantidade_horas int not null,
    data_hora date not null,
    usuarios_id bigint not null,
    primary key (id)
);

ALTER TABLE horas_apontadas ADD CONSTRAINT fk_horas_projetos
FOREIGN KEY (projetos_id) REFERENCES projetos(id);

ALTER TABLE horas_apontadas ADD CONSTRAINT fk_consultor_horas
FOREIGN KEY (usuarios_id) REFERENCES usuarios(id);