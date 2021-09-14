ALTER TABLE fornecedores
ADD COLUMN email VARCHAR(100) NOT NULL;

UPDATE fornecedores SET email = "danarts.desenhos@gmail.com";