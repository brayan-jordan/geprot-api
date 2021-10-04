package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ConsultorAssembler;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.repository.ConsultorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ConsultorService {
    private ConsultorAssembler consultorAssembler;
    private ConsultorRepository consultorRepository;

    @Transactional
    public Consultor cadastrar(Consultor consultor) {
        return consultorRepository.save(consultor);
    }


}
