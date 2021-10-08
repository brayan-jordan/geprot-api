package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultorAssembler;
import net.weg.gestor.api.model.ConsultorDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.ConsultorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ConsultorService {
    private ConsultorAssembler consultorAssembler;
    private ConsultorRepository consultorRepository;

    @Transactional
    public Consultor cadastrar(Consultor consultor) {
        return consultorRepository.save(consultor);
    }

    public ConsultorDTO buscarConsultor(long consultorId) {
        return consultorAssembler.toModel(consultorRepository.findById(consultorId)
                .orElseThrow(() -> new NegocioException("Consultor não encontrado")));
    }
}
