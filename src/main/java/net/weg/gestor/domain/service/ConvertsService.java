package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.CCPagantesAssembler;
import net.weg.gestor.api.assembler.ProjetoAssembler;
import net.weg.gestor.api.model.ProjetoInteiroDTO;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ConvertsService {

    private ProjetoRepository projetoRepository;
    private ProjetoAssembler projetoAssembler;
    private CCPagantesRepository ccPagantesRepository;
    private CCPagantesAssembler ccPagantesAssembler;

//    public ProjetoInteiroDTO convertProject(Long projetoId) {
//        ProjetoInteiroDTO projeto = new ProjetoInteiroDTO();
//        projeto.setProjeto(
//                projetoAssembler.toModel(projetoRepository.findByIdProjeto(projetoId)));
//        projeto.setCcpagantes(
//                ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findByIdProjeto(projetoId)));
//
//        return projeto;
//    }

//    public ArrayList<ProjetoInteiroDTO> convertProjectList(List<Projeto> projetos) {
//        ArrayList<ProjetoInteiroDTO> listProjects = new ArrayList<>();
//        for (int i = 0; i < projetos.size(); ++i) {
//            listProjects.add(convertProject(projetos.get(i).getId()));
//        }
//
//        return listProjects;
//    }

}
