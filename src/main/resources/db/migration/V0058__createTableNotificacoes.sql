CREATE TABLE notificacoes (
    id bigint not null auto_increment,
    descricao varchar(200) not null,
    usuarios_id bigint not null,
    data date not null,
    status_leitura int,
    primary key (id)
);

ALTER TABLE notificacoes ADD CONSTRAINT fk_usuarios_notificacoes
FOREIGN KEY (usuarios_id) REFERENCES usuarios (id);