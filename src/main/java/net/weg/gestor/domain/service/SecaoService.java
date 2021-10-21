package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.SecaoAssembler;
import net.weg.gestor.api.map.UsuarioAssembler;
import net.weg.gestor.api.model.SecaoDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SecaoService {

    private SecaoRepository secaoRepository;
    private SecaoAssembler secaoAssembler;

    public SecaoDTO buscar(Long secaoId) {
        return secaoAssembler.toModel(secaoRepository.findById(secaoId).orElseThrow(
            () -> new NegocioException("Secao nao encontrada"))
        );
    }

    public List<SecaoDTO> listarTodas() {
        return secaoAssembler.toCollectionModel(secaoRepository.findAll());
    }

    public SecaoDTO buscarSecao(long secaoId) {
        return secaoAssembler.toModel(secaoRepository.findById(secaoId).orElseThrow(
                () -> new NegocioException("Seção não encontrada"))
        );
    }

}
