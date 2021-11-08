ALTER TABLE gestores ADD CONSTRAINT fk_gestores_secoes
FOREIGN KEY (secoes_id) REFERENCES secoes (id);

ALTER TABLE consultores ADD CONSTRAINT fk_consultores_fornecedor
FOREIGN KEY (fornecedores_id) REFERENCES fornecedores (id);

ALTER TABLE gestores ADD CONSTRAINT fk_gestores_usuario
FOREIGN KEY (usuarios_id) REFERENCES usuarios (id);

ALTER TABLE consultores ADD CONSTRAINT fk_consultores_usuario
FOREIGN KEY (usuarios_id) REFERENCES usuarios (id);

ALTER TABLE role_usuarios ADD CONSTRAINT fk_role_usuarios
FOREIGN KEY (usuarios_id) REFERENCES usuarios(id);

ALTER TABLE role_usuarios ADD CONSTRAINT fk_role_nome
FOREIGN KEY (role_nome) REFERENCES role (nome);

ALTER TABLE horas_apontadas ADD CONSTRAINT fk_horas_projetos
FOREIGN KEY (projetos_id) REFERENCES projetos(id);

ALTER TABLE horas_apontadas ADD CONSTRAINT fk_consultor_horas
FOREIGN KEY (consultores_id) REFERENCES consultores (id);

ALTER TABLE consultores_alocados ADD CONSTRAINT fk_projetos_alocados
FOREIGN KEY (projetos_id) REFERENCES projetos(id);

ALTER TABLE consultores_alocados ADD CONSTRAINT fk_usuarios_alocados
FOREIGN KEY (consultores_id) REFERENCES consultores(id);

ALTER TABLE cc_pagantes ADD CONSTRAINT fk_ccpagantes_projeto
FOREIGN KEY (projetos_id) REFERENCES projetos (id);

ALTER TABLE cc_pagantes ADD CONSTRAINT fk_ccpagantes_secoes
FOREIGN KEY (secoes_id) REFERENCES secoes (id);

ALTER TABLE notificacoes ADD CONSTRAINT fk_usuarios_notificacoes
FOREIGN KEY (usuarios_id) REFERENCES usuarios (id);

ALTER TABLE skills_consultores ADD CONSTRAINT fk_skill_id
FOREIGN KEY (skill_id) REFERENCES skill (id);

ALTER TABLE skills_consultores ADD CONSTRAINT fk_skill_consultor
FOREIGN KEY (consultores_id) REFERENCES consultores (id);