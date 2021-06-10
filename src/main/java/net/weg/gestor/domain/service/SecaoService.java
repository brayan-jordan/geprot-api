package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Secao;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class SecaoService {

    private SecaoRepository secaoRepository;

    @Transactional
    public Secao buscar(Long secaoId) {
        return secaoRepository.findById(secaoId).
                orElseThrow(() -> new NegocioException("Nao foi encontrado uma seção com esse ID"));
    }

    @Transactional
    public List<Secao> listar() {
        return secaoRepository.findAll();
    }

}
