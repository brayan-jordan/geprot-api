package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.CCPagantesAssembler;
import net.weg.gestor.api.model.SecoesDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInputDTO;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CCPagantesService {

    private CCPagantesAssembler ccPagantesAssembler;
    private final CCPagantesRepository ccPagantesRepository;

//    public List<SecoesDTO> listartodos() {
//        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findAll());
//
//    }

//    public void saveCcPagantes(ProjetoInputDTO project, Long idCadastro) {
//        List<CCPagantes> newCcPagantes = ccPagantesAssembler.toCollectionEntity(project.getCcpagantes());
//        for (int i = 0; i < newCcPagantes.size(); ++i) {
//            newCcPagantes.get(i).setProjetos_id(idCadastro);
//        }
//        ccPagantesRepository.saveAll(newCcPagantes);
//    }

//    public List<SecoesDTO> listarporprojeto(Long projetoid) {
//        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findByIdCC(projetoid));
//
//    }

}
