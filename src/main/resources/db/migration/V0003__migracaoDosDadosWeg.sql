INSERT INTO secoes VALUES (null, "Centro weg", 500000.00);
INSERT INTO role VALUES ("ROLE_GESTOR");
INSERT INTO role VALUES ("ROLE_CONSULTOR");
INSERT INTO fornecedores VALUES (1,"CentroWeg","centroweg@gmail.com");
INSERT INTO usuarios VALUES
(null, "Brayan Vinicius Jordan", "brayan@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_GESTOR",1);
INSERT INTO gestores VALUES (67264, 1, 1);

INSERT INTO projetos VALUES (null,'Gerenciamento de demandas', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '0', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'PGDMS', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '1', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Realizar plano de testes', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '2', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Projeto parque', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '3', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Geprot', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '2', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Projeto TDSX', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '3', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Projeto QFD', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '3', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Brendon', 'Guizada');

INSERT INTO cc_pagantes VALUES (null, 1,1,100);

INSERT INTO cc_pagantes VALUES (null, 2,1,100);

INSERT INTO cc_pagantes VALUES (null, 3,1,100);

INSERT INTO cc_pagantes VALUES (null, 4,1,100);

INSERT INTO cc_pagantes VALUES (null, 5,1,100);

INSERT INTO cc_pagantes VALUES (null, 6,1,100);

INSERT INTO cc_pagantes VALUES (null, 7,1,100);

INSERT INTO usuarios VALUES
(null, "Brendon consultor", "brendon@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR",1);

INSERT INTO consultores VALUES
(123, 2, 1, 12);


INSERT INTO consultores_alocados VALUES
(null, 1, 123, 120, 120);

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 123, "Trabalho bem feito, parabéns", 2);

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 123, "Java e Mysql", 2);

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 123, "Planejando testes e executando", 2);

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 123, "Descrição das horas apontadas", 2);

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 123, "Coisa linda de hora apontada", 2);

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 123, "De certo vai dar boa demais", 2);