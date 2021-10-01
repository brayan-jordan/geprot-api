package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInputDTO;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.domain.model.ConsultoresAlocados;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
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
    private ConsultoresAlocadosRepository consultoresAlocadosRepository;
    private CCPagantesRepository ccPagantesRepository;

    public Projeto toEntity(ProjetoInputDTO projetoInputDTO) {
        return modelMapper.map(projetoInputDTO, Projeto.class);

    }

//    public ProjetoDTO toModel(Projeto projeto) {
//        for (int i = 0; i < projeto.getConsultores().size(); ++i) {
//            projeto.getConsultores().get(i).set(
//                    usuarioRepository.findByIdUsuario(projeto.getConsultores().get(i).getId()).getNome());
//        }
//        List<CCPagantes> teste = ccPagantesRepository.findByIdCC(projeto.getId());
//        ProjetoDTO projectReturn = modelMapper.map(projeto, ProjetoDTO.class);
//        for (int i = 0; i < projectReturn.getUsuarios().size(); ++i) {
//            ConsultoresAlocados buscarHoras = consultoresAlocadosRepository.buscar(
//                    projectReturn.getUsuarios().get(i).getId(), projectReturn.getId()
//            );
//            projectReturn.getUsuarios().get(i).setLimiteHoras(buscarHoras.getLimiteHoras());
//        }
//
//        for (int i = 0; i < teste.size(); ++i) {
//            projectReturn.getSecaos().get(i).setTaxa(teste.get(i).getTaxa());
//        }
//        projectReturn.setHorasRestantes(projectReturn.getHorasPrevistas() - projectReturn.getHorasTrabalhadas());
//        projectReturn.setValorRestante(projectReturn.getValor() - projectReturn.getValorUtilizado());
//
//        for (int i = 0; i < projectReturn.getSecaos().size(); i++){
//            double valorPagoCC = projectReturn.getSecaos().get(i).getTaxa() * projectReturn.getValor();
//            projectReturn.getSecaos().get(i).setVerba(valorPagoCC / 100);
//        }
//
//         projectReturn.setBarraProgresso((projectReturn.getHorasTrabalhadas() * 100) / projectReturn.getHorasPrevistas());
//        return projectReturn;
//    }

//    public List<ProjetoDTO> toCollectionModel(List<Projeto> projetos) {
//        return projetos.stream().map(this::toModel).collect(Collectors.toList());
//    }

}
