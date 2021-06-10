create table projetos (
    idprojeto int NOT NULL auto_increment,
    nomeprojeto varchar(100) NOT NULL,
    datainicio date NOT NULL,
    datafinalizacao date,
    horasprevistas int NOT NULL,
    horastrabalhadas int,
    valorprojeto double(20,2) not null,
    valorutilizado double(20,2),
    valorrestante double(20,2) not null,
    gestorid bigint not null,
    statusprojeto varchar(20) not null,
    PRIMARY KEY(idprojeto)
);