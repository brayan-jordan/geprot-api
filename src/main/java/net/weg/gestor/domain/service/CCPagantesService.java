package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.CCPagantesAssembler;
import net.weg.gestor.api.model.CCPagantesDTO;
import net.weg.gestor.api.model.centrodecustoinputDTO.CCPagantesInputDTO;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.CentroDeCustoRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CCPagantesService {

    private ValidationsService validationsService;
    private CCPagantesAssembler ccPagantesAssembler;
    private final CCPagantesRepository ccPagantesRepository;
    private CentroDeCustoRepository centroDeCustoRepository;
    private ProjetoRepository projetoRepository;

    public List<CCPagantesDTO> listartodos() {
        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findAll());

    }

    public List<CCPagantesDTO> listarporprojeto(Long projetoid) {
        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findByIdProjeto(projetoid));

    }

    public List<CCPagantesDTO> cadastrar(List<CCPagantesInputDTO> ccPagantesInputDTO) {
        validationsService.verificacoesCCpagantes(ccPagantesInputDTO);

        List<CCPagantes> ccPagantes = ccPagantesAssembler.toCollectionEntity(ccPagantesInputDTO);
        for (int i = 0; i < ccPagantes.size(); ++i) {
            ccPagantes.get(i).setCentrodecusto(centroDeCustoRepository.findById2
                    (ccPagantes.get(i).getCentrodecusto().getId()));
            ccPagantes.get(i).setProjeto(projetoRepository.findByIdProjeto(
                    ccPagantes.get(i).getProjeto().getId()));
            ccPagantesRepository.save(ccPagantes.get(i));
        }

        return ccPagantesAssembler.toCollectionModel(ccPagantes);
    }

}
