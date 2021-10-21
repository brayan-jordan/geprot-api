INSERT INTO secoes VALUES (null, "Centro weg", "Arthur", 5000.00);
INSERT INTO role VALUES ("ROLE_GESTOR");
INSERT INTO role VALUES ("ROLE_CONSULTOR");
INSERT INTO fornecedores VALUES (1,"CentroWeg","centroweg@gmail.com");
INSERT INTO usuarios VALUES
(null, "Brayan Vinicius Jordan", "brayan@gmail.com", 20200202, 0, "$2a$10$Q8nuSLSscslGgdAux4NSkencXRmriLAuJXoOKLahJGbNlqfKsi8ZS");
INSERT INTO role_usuarios VALUES (null,"ROLE_GESTOR",1);
INSERT INTO gestores VALUES (67264, 1, 1);

INSERT INTO projetos VALUES (null,'Teste', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '0', 'Dale garaio', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Projeto legal', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '1', 'Dale garaio', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Jonathan', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '2', 'Dale garaio', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Guizada', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '3', 'Dale garaio', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Robert', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '2', 'Dale garaio', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Brayan', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '3', 'Dale garaio', 'Brendon', 'Guizada');

INSERT INTO projetos VALUES (null,'Brendon', '2020-02-02', '2020-02-08', '2020-02-02', '67', '12', '1500', '1111', '3', 'Dale garaio', 'Brendon', 'Guizada');

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
(null, 1, 123, 120, 60);

INSERT INTO horas_apontadas VALUES
(null, 1, 20, 20200202, 123, "Trabalho bem feito, parabéns", 2);