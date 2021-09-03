package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputCCPagDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputDTO;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.api.model.CCPagantesDTO;
import net.weg.gestor.api.model.centrodecustoinputDTO.CCPagantesInputDTO;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.CentroDeCustoRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CCPagantesAssembler {

    private ModelMapper modelMapper;
    private ProjetoRepository projetoRepository;
    private CentroDeCustoRepository centroDeCustoRepository;

    public CCPagantes toEntity(CCPagantesInputDTO ccPagantesInputDTO) {
        return modelMapper.map(ccPagantesInputDTO, CCPagantes.class);
    }

    public CCPagantes toEntity(CCPagantesDTO ccPagantesDTO) {
        return modelMapper.map(ccPagantesDTO, CCPagantes.class);
    }

    public CCPagantes toEntityInput(ProjectInputCCPagDTO ccPagantesDTO) {
        return modelMapper.map(ccPagantesDTO, CCPagantes.class);
    }

    public CCPagantesDTO toModel(CCPagantes ccPagantes) {
        CCPagantesDTO ccPagantesDTO = modelMapper.map(ccPagantes, CCPagantesDTO.class);
        ccPagantesDTO.setValor(projetoRepository.findByIdProjeto(ccPagantes.getProjetos_id()).getValor() / 100 * ccPagantesDTO.getTaxa());
        ccPagantesDTO.setNome(centroDeCustoRepository.buscar(ccPagantes.getCentros_de_custo_id()).getNome());
        return ccPagantesDTO;
    }

    public List<CCPagantesDTO> toCollectionModel(List<CCPagantes> ccPagantes) {
        return ccPagantes.stream().map(this::toModel).collect(Collectors.toList());
    }

    public List<CCPagantes> toCollectionEntity(List<ProjectInputCCPagDTO> ccPagantes) {
        return ccPagantes.stream().map(this::toEntityInput).collect(Collectors.toList());
    }
}
