CREATE TABLE usuarios(
id bigint primary key auto_increment,
senha varchar(100) not null,
email varchar(100) not null
);

ALTER TABLE gestores DROP COLUMN senha;
ALTER TABLE gestores DROP COLUMN email;