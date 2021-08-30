ALTER TABLE projetos
CHANGE horasprevistas horas_previstas int NOT NULL;

ALTER TABLE projetos
CHANGE horastrabalhadas horas_trabalhadas int;

ALTER TABLE projetos
CHANGE valorutilizado valor_utilizado double(20, 2);

ALTER TABLE projetos
DROP COLUMN valorrestante