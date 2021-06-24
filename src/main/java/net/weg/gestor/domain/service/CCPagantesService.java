package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.assembler.CCPagantesAssembler;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.model.CCPagantesModel;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CCPagantesService {

    private CCPagantesAssembler ccPagantesAssembler;
    private CCPagantesRepository ccPagantesRepository;

    public List<CCPagantesModel> listartodos() {
        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findAll());

    }

}
