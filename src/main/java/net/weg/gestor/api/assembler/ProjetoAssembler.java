package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ProjetoInteiroDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputDTO;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.CentroDeCustoRepository;
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
    private CentroDeCustoRepository centroDeCustoRepository;

    public Projeto toEntity(ProjectInputDTO projetoInputDTO) {
        return modelMapper.map(projetoInputDTO, Projeto.class);

    }

    public ProjetoDTO toModel(Projeto projeto) {
        for (int i = 0; i < projeto.getUsuarios().size(); ++i) {
            projeto.getUsuarios().get(i).setNome(
                    usuarioRepository.findByIdUsuario(projeto.getUsuarios().get(i).getId()).getNome());
        }
        List<CCPagantes> teste = ccPagantesRepository.findByIdCC(projeto.getId());
        ProjetoDTO projectReturn = modelMapper.map(projeto, ProjetoDTO.class);
        for (int i = 0; i < teste.size(); ++i) {
            projectReturn.getCentroDeCustos().get(i).setTaxa(teste.get(i).getTaxa());
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
