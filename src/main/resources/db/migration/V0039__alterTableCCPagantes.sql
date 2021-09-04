ALTER TABLE cc_pagantes
CHANGE projeto_id projetos_id bigint not null;

ALTER TABLE cc_pagantes ADD CONSTRAINT fk_ccpagantes_projeto
FOREIGN KEY (projetos_id) REFERENCES projetos(id);

ALTER TABLE cc_pagantes ADD CONSTRAINT fk_ccpagantes_centrodecusto
FOREIGN KEY (secoes_id) REFERENCES secoes(id);