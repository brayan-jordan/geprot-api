UPDATE secoes SET nome_responsavel = "Marasco" where id = 1;
UPDATE secoes SET nome_responsavel = "Natalino" where id = 2;

INSERT INTO projetos VALUES (null, "Limpeza dos pátios", 20210202, 20210202, 500, 200, 43029, 23029, "CONCLUIDO", 20210202,
 "Projeto para comprarmos uma bike para o Professor Roberto, devido a ele ser um ótimo professor e chorar todos os dias por nao ter uma bike adequada para seus roles",
 "Roberto fotografias", "Alunos da MI68");

 INSERT INTO projetos VALUES (null, "Daniela desenhos", 20210202, 20210202, 500, 200, 43029, 23029, "NAO_INICIADO", 20210202,
  "Daniela quer subir o nivel dos seus desenhos, para isso precisa de uma tabua para desenhar, vamos providenciar familia",
  "Dani arts", "Apenas demais");

  INSERT INTO projetos VALUES (null, "Tatuagem da medusa", 20210202, 20210202, 500, 200, 43029, 23029, "ATRASADO", 20210202,
   "Brendon está desejando uma tatuagem da medusa, porém nao esta conseguindo, o objetivo desse projeto é ajudalo",
   "Brendon triste", "Tatuador tatuapé");

INSERT INTO cc_pagantes VALUES
(null, 1, 2, 100),
(null, 1, 3, 100),
(null, 1, 4, 100)