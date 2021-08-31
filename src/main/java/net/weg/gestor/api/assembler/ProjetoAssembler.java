package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ProjetoInteiroDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputDTO;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProjetoAssembler {

    private ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;
    private CCPagantesRepository ccPagantesRepository;

    public Projeto toEntity(ProjectInputDTO projetoInputDTO) {
        return modelMapper.map(projetoInputDTO, Projeto.class);

    }

    public ProjetoDTO toModel(Projeto projeto) {
        for (int i = 0; i < projeto.getConsultores().size(); ++i) {
            projeto.getConsultores().get(i).setNome(
                    usuarioRepository.findByIdUsuario(projeto.getConsultores().get(i).getId()).getNome());
        }
        ProjetoDTO projectReturn = modelMapper.map(projeto, ProjetoDTO.class);
        for (int i = 0; i < projectReturn.getCcpagantes().size(); ++i) {
            projectReturn.getCcpagantes().get(i).setTaxa(
                    ccPagantesRepository.findByIdCC(projectReturn.getCcpagantes().get(i).getId()).getTaxa());
        }
        return projectReturn;
    }

    public List<ProjetoDTO> toCollectionModel(List<Projeto> projetos) {
        return projetos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public ProjetoInteiroDTO toModelInteiro(ProjectInputDTO projeto) {
        return modelMapper.map(projeto, ProjetoInteiroDTO.class);
    }
}
