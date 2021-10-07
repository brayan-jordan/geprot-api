package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.CCPaganteDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CCPagantesAssembler {

    private ModelMapper modelMapper;
    private ProjetoRepository projetoRepository;
    private SecaoRepository secaoRepository;

    public CCPaganteDTO toModel(CCPagantes ccPagante, Projeto projeto) {
        CCPaganteDTO ccPagantes = modelMapper.map(ccPagante, CCPaganteDTO.class);
        ccPagantes.setId(ccPagante.getSecao().getId());
        ccPagantes.setNome(ccPagante.getSecao().getNome());
        ccPagantes.setValorPagante(projeto.getValor() * 100 / ccPagante.getTaxa());
        return ccPagantes;
    }

    public ArrayList<CCPaganteDTO> toCollectionModel(List<CCPagantes> ccPagantes, Projeto projeto) {
        ArrayList<CCPaganteDTO> listaCCPagantes = new ArrayList<CCPaganteDTO>();
        ccPagantes.forEach(ccPagantes1 -> {
            CCPaganteDTO ccPagantesDTO = toModel(ccPagantes1, projeto);
            listaCCPagantes.add(ccPagantesDTO);
        });
        return listaCCPagantes;
    }

}
