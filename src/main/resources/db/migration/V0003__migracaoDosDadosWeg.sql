INSERT INTO secoes VALUES (null, "Weg Digitals");
INSERT INTO secoes VALUES (null, "Ferramentaria");
INSERT INTO secoes VALUES (null, "Seção 3");
INSERT INTO secoes VALUES (null, "WU75");
INSERT INTO secoes VALUES (null, "Direção Geral");
INSERT INTO role VALUES ("ROLE_GESTOR");
INSERT INTO role VALUES ("ROLE_CONSULTOR");
INSERT INTO fornecedores VALUES (1,"Fornecedores","fornecedores@gmail.com");
INSERT INTO fornecedores VALUES (2,"Pichau","fornecedores@gmail.com");
INSERT INTO fornecedores VALUES (3,"Kabum","fornecedores@gmail.com");
INSERT INTO fornecedores VALUES (4,"Amazon","fornecedores@gmail.com");

INSERT INTO usuarios VALUES
(null, "Brayan Vinicius Jordan", "brayan@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_GESTOR",1);
INSERT INTO gestores VALUES (67264, 1, 1);

INSERT INTO projetos VALUES (null,'GDTWEG', '2020-02-02', '2020-02-08', '2020-02-02', '00001', '120', '120', '1500', '1111', '0', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Felipe Silveira', 'Guilherme Lynz');
INSERT INTO projetos VALUES (null,'PGMWEG', '2020-02-02', '2021-11-04', '2020-02-02', '00001', '67', '0', '1500', '1111', '1', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Maicon da Silva', 'Jackson Felipe');
INSERT INTO projetos VALUES (null,'Planificar testes', '2020-02-02', '2020-02-08', '2020-02-02', '00001', '67', '0', '1500', '1111', '2', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Milena Felipina', 'Rodrigo Kelbert');
INSERT INTO projetos VALUES (null,'Projeto parque', '2020-02-02', '2020-02-08', '2020-02-02', '00001', '67', '0', '1500', '1111', '3', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Maira', 'Felipe Zolz');
INSERT INTO projetos VALUES (null,'Geprot', '2020-02-02', '2020-02-08', '2020-02-02', '00001', '67', '0', '1500', '1111', '2', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Marcos', 'Robert da Costa');
INSERT INTO projetos VALUES (null,'Projeto TDSX', '2020-02-02', '2020-02-08', '2020-02-02', '00001', '67', '0', '1500', '1111', '3', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Felipe', 'Guizada');
INSERT INTO projetos VALUES (null,'Projeto QFD', '2020-02-02', '2020-02-08', '2020-02-02', '00001', '67', '0', '1500', '1111', '3', 'Descricao de um projeto interessante, porem apenas escrevendo algumas coisas para preencher o campo em branco, visando o tamanho da div', 'Brendon', 'Guizada');

INSERT INTO cc_pagantes VALUES (null, 1,1,30);
INSERT INTO cc_pagantes VALUES (null, 1,2,70);
INSERT INTO cc_pagantes VALUES (null, 2,1,30);
INSERT INTO cc_pagantes VALUES (null, 2,2,70);
INSERT INTO cc_pagantes VALUES (null, 3,1,30);
INSERT INTO cc_pagantes VALUES (null, 3,3,70);
INSERT INTO cc_pagantes VALUES (null, 4,1,30);
INSERT INTO cc_pagantes VALUES (null, 4,4,70);
INSERT INTO cc_pagantes VALUES (null, 5,1,30);
INSERT INTO cc_pagantes VALUES (null, 5,2,70);
INSERT INTO cc_pagantes VALUES (null, 6,1,30);
INSERT INTO cc_pagantes VALUES (null, 6,2,70);
INSERT INTO cc_pagantes VALUES (null, 7,3,30);
INSERT INTO cc_pagantes VALUES (null, 7,1,70);


INSERT INTO skill VALUES
(null, "JAVA"),(null, "Abape"),(null, "CSS"),(null, "HTML"),(null, "React JS"),(null, "React Native"),
(null, "Ruby"),(null, "Angular"),(null, "C#"),(null, "C"),(null, "C++"),(null, "JavaScript"),(null, "Kotlin"),
(null, "Elixir"),(null, "Deployment"),(null, "Figma"),(null, "Phyton"), (null, "Skill Default");

INSERT INTO usuarios VALUES
(null, "Brendon consultor", "brendon@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR",1);

INSERT INTO consultores VALUES
(null, 2, 1, 12);

INSERT INTO usuarios VALUES
(null, "Daniela", "daniela@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR",1);

INSERT INTO consultores VALUES
(null, 3, 1, 12);

INSERT INTO usuarios VALUES
(null, "Jean", "jean@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR",1);

INSERT INTO consultores VALUES
(null, 4, 1, 12);

INSERT INTO usuarios VALUES
(null, "Gabriel", "gabriel@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR",1);

INSERT INTO consultores VALUES
(null, 5, 1, 12);

INSERT INTO usuarios VALUES
(null, "Fernando", "fernando@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR",1);

INSERT INTO consultores VALUES
(null, 6, 1, 12);

INSERT INTO usuarios VALUES
(null, "Marcia", "marcia@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR",1);

INSERT INTO consultores VALUES
(null, 7, 1, 12);

INSERT INTO usuarios VALUES
(null, "Lucia", "lucia@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_CONSULTOR",1);

INSERT INTO consultores VALUES
(null, 8, 1, 12);

INSERT INTO consultores_alocados VALUES
(null, 1, 1, 120, 120, 1);

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 1, "Trabalho bem feito, parabéns", 2, "-");

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 1, "Java e Mysql", 2, "-");

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 1, "Planejando testes e executando", 2, "-");

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 1, "Descrição das horas apontadas", 2, "-");

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 1, "Coisa linda de hora apontada", 2, "-");

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 1, "De certo vai dar boa demais", 2, "-");

