CREATE TABLE secoes (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    nome_responsavel varchar(100) not null,
    verba double(10, 2) not null,
    primary key (id)
);

CREATE TABLE cc_pagantes (
    id bigint not null auto_increment,
    projetos_id bigint not null,
    secoes_id bigint not null,
    taxa double(10, 2) not null,
    primary key (id)
);

CREATE TABLE consultores (
    id bigint not null auto_increment,
    usuarios_id bigint not null,
    fornecedores_id bigint not null,
    preco_hora double(10, 2) not null,
    primary key (id)
);

CREATE TABLE consultores_alocados (
    id bigint not null auto_increment,
    projetos_id bigint not null,
    consultores_id bigint not null,
    limite_horas int not null,
    horas_apontadas int not null,
    primary key(id)
);

CREATE TABLE fornecedores (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    primary key(id)
);

CREATE TABLE gestores (
    id bigint not null auto_increment,
    secoes_id bigint not null,
    usuarios_id bigint not null,
    primary key(id)
);

CREATE TABLE horas_apontadas (
    id bigint not null auto_increment,
    projetos_id bigint not null,
    quantidade_horas int not null,
    data date not null,
    consultores_id bigint not null,
    descricao_trabalho varchar(300),
    status VARCHAR(15),
    primary key (id)
);

CREATE TABLE notificacoes (
    id bigint not null auto_increment,
    descricao varchar(200) not null,
    usuarios_id bigint not null,
    data date not null,
    status_leitura int,
    tipo varchar(30) not null,
    primary key (id)
);

CREATE TABLE projetos (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    data_inicio date,
    data_finalizacao date,
    data_cadastro date,
    horas_previstas int not null,
    horas_trabalhadas int,
    valor (10, 2) not null,
    valor_utilizado double(10,2),
    status varchar(20) not null,
    descricao varchar(1000) not null,
    nome_solicitante varchar(50) not null,
    nome_responsavel varchar(50) not null,
    primary key (id)
);

CREATE TABLE role (
    nome varchar(20)
);

CREATE TABLE role_usuarios (
    id bigint not null auto_increment,
    role_nome varchar(60),
    usuarios_id bigint not null,
    primary key (id)
);

CREATE TABLE secoes (
    id bigint not null auto_increment,
    nome varchar(50) not null,
    nome_responsavel varchar(100) not null,
    verba double(10,2) not null,
    primary key (id)
);

CREATE TABLE usuarios (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    data_cadastro date not null,
    status varchar(20) not null,
    senha varchar(100) not null,
    primary key(id)
);




