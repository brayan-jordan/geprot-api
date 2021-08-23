package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.CCPagantesAssembler;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.CentroDeCustoRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.api.model.CCPagantesDTO;
import net.weg.gestor.api.model.centrodecustoinputDTO.CCPagantesInputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CCPagantesService {

    private CCPagantesAssembler ccPagantesAssembler;
    private CCPagantesRepository ccPagantesRepository;
    private CentroDeCustoRepository centroDeCustoRepository;
    private ProjetoRepository projetoRepository;

    public List<CCPagantesDTO> listartodos() {
        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findAll());

    }

    public List<CCPagantesDTO> listarporprojeto(Long projetoid) {
        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findByIdProjeto(projetoid));

    }

    public CCPagantesDTO cadastrar(CCPagantesInputDTO ccPagantesInputDTO) {
        if (ccPagantesInputDTO.getTaxa() > 100 || ccPagantesInputDTO.getTaxa() < 1) {
            throw new NegocioException("Insira uma taxa válida");
        }

        if (valorTotalTaxa(ccPagantesInputDTO.getProjeto().getId(), ccPagantesInputDTO.getTaxa()) > 100) {
            throw new NegocioException("Esse valor ultrapassa o limite de 100");
        }

        CCPagantes ccPagantes = ccPagantesAssembler.toEntity(ccPagantesInputDTO);

        boolean validation = centroDeCustoRepository.findById(
                ccPagantes.getCentrodecusto().getId()).isPresent();

        if (!validation) {
            throw new NegocioException("ID De centro de custo inválido, tente novamente");
        }

        ccPagantes.setCentrodecusto(centroDeCustoRepository.findById2(ccPagantes.getCentrodecusto().getId()));
        boolean validation2 = projetoRepository.findByIdProjeto2(ccPagantes.getProjeto().getId()).isPresent();

        if (!validation2) {
            throw new NegocioException("ID Do Projeto inválido");
        }

        ccPagantes.setProjeto(projetoRepository.findByIdProjeto(ccPagantes.getProjeto().getId()));
        ccPagantesRepository.save(ccPagantes);
        return ccPagantesAssembler.toModel(ccPagantes);
    }

    public int valorTotalTaxa(Long projetoid, int newtaxa) {
        List<CCPagantesDTO> lista = listarporprojeto(projetoid);

        int soma= 0;
        for (int i = 0; i < lista.size(); ++i) {
            soma += lista.get(i).getTaxa();
        }

        return soma + newtaxa;
    }

}
