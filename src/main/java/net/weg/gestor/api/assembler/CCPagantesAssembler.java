package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CCPagantesAssembler {

    private ModelMapper modelMapper;
    private ProjetoRepository projetoRepository;
    private SecaoRepository secaoRepository;

//    public CCPagantes toEntity(CCPagantesInputDTO ccPagantesInputDTO) {
//        return modelMapper.map(ccPagantesInputDTO, CCPagantes.class);
//    }
//
//    public CCPagantes toEntity(SecoesDTO secoesDTO) {
//        return modelMapper.map(secoesDTO, CCPagantes.class);
//    }

//    public CCPagantes toEntityInput(AlocarCCPagantesInputDTO ccPagantesDTO) {
//        return modelMapper.map(ccPagantesDTO, CCPagantes.class);
//    }

//    public SecoesDTO toModel(CCPagantes ccPagantes) {
//        SecoesDTO secoesDTO = modelMapper.map(ccPagantes, SecoesDTO.class);
//        secoesDTO.setVerba(projetoRepository.findByIdProjeto(ccPagantes.getProjeto().getId()).getValor() / 100 * secoesDTO.getTaxa());
//        secoesDTO.setNome(secaoRepository.findByIdAux(ccPagantes.getSecao().getId()).getNome());
//        return secoesDTO;
//    }

//    public List<SecoesDTO> toCollectionModel(List<CCPagantes> ccPagantes) {
//        return ccPagantes.stream().map(this::toModel).collect(Collectors.toList());
//    }

//    public List<CCPagantes> toCollectionEntity(List<AlocarCCPagantesInputDTO> ccPagantes) {
//        return ccPagantes.stream().map(this::toEntityInput).collect(Collectors.toList());
//    }
}
