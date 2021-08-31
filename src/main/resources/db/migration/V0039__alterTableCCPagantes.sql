ALTER TABLE cc_pagantes
CHANGE centrodecusto_id centros_de_custo_id bigint not null;

ALTER TABLE cc_pagantes
CHANGE projeto_id projetos_id bigint not null;

ALTER TABLE cc_pagantes ADD CONSTRAINT fk_ccpagantes_projeto
FOREIGN KEY (projetos_id) REFERENCES projetos(id);

ALTER TABLE cc_pagantes ADD CONSTRAINT fk_ccpagantes_centrodecusto
FOREIGN KEY (centros_de_custo_id) REFERENCES centros_de_custo(id);