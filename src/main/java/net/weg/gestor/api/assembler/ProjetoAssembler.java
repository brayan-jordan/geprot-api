package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.CCPaganteDTO;
import net.weg.gestor.api.model.ProjetoCardDTO;
import net.weg.gestor.api.model.ProjetoDetalhadoDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProjetoAssembler {

    private ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;
    private ConsultoresAlocadosRepository consultoresAlocadosRepository;
    private CCPagantesRepository ccPagantesRepository;


    public ProjetoCardDTO toModel(Projeto projeto) {
        ProjetoCardDTO projetoCardDTO = modelMapper.map(projeto, ProjetoCardDTO.class);
        projetoCardDTO.setValorRestante(projetoCardDTO.getValor() - projetoCardDTO.getValorUtilizado());
        return projetoCardDTO;
    }

    public List<ProjetoCardDTO> toCollectionModel(List<Projeto> projetos) {
        return projetos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public ProjetoDetalhadoDTO toModelDetalhada(Projeto projeto, List<CCPagantes> ccPagantes) {
        ProjetoDetalhadoDTO projetoDetalhado = modelMapper.map(projeto, ProjetoDetalhadoDTO.class);
        List<CCPaganteDTO> ccPagantesProjeto = new ArrayList<>();
        ccPagantes.forEach(ccpagante -> {
            ccPagantesProjeto.add(new CCPaganteDTO(
                    ccpagante.getSecao().getId(),
                    ccpagante.getSecao().getNome(),
                    ccpagante.getTaxa()
            ));
        });
        projetoDetalhado.setHorasRestantes(projetoDetalhado.getHorasPrevistas() - projetoDetalhado.getHorasTrabalhadas());
        projetoDetalhado.setValorRestante(projetoDetalhado.getValor() - projetoDetalhado.getValorUtilizado());
        return projetoDetalhado;

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
