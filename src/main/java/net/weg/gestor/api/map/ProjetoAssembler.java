package net.weg.gestor.api.map;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.projeto.ProjetoAlocarDTO;
import net.weg.gestor.api.model.projeto.ProjetoCardDTO;
import net.weg.gestor.api.model.projeto.ProjetoDetalhadoDTO;
import net.weg.gestor.api.model.cadastrarprojetoinput.ProjetoConsultoresInputDTO;
import net.weg.gestor.api.model.cadastrarprojetoinput.ProjetoInputDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.entities.StatusProjeto;
import net.weg.gestor.domain.repository.ConsultorRepository;
import net.weg.gestor.domain.service.ConsultoresAlocadosService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProjetoAssembler {

    private ModelMapper modelMapper;
    private ConsultoresAlocadosService consultoresAlocadosService;
    private ConsultorRepository consultorRepository;


    public ProjetoCardDTO toModel(Projeto projeto) {
        ProjetoCardDTO projetoCardDTO = modelMapper.map(projeto, ProjetoCardDTO.class);
        projetoCardDTO.setValorRestante(projetoCardDTO.getValor() - projetoCardDTO.getValorUtilizado());
        projetoCardDTO.setBarraProgresso((projetoCardDTO.getHorasTrabalhadas() * 100)/ projetoCardDTO.getHorasPrevistas());
        return projetoCardDTO;
    }

    public List<ProjetoCardDTO> toCollectionModel(List<Projeto> projetos) {
        return projetos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public ProjetoAlocarDTO toModelAlocado(Projeto projeto, Consultor consultor) {
        ProjetoAlocarDTO projetoAlocar = modelMapper.map(projeto, ProjetoAlocarDTO.class);
        projetoAlocar.setAllocated(consultoresAlocadosService.verificaSeConsultorEstaAlocadoEmProjeto(projeto, consultor));
        return projetoAlocar;
    }

    public List<ProjetoAlocarDTO> toCollectionModelAlocado(List<Projeto> projetos, Consultor consultor) {
        ArrayList<ProjetoAlocarDTO> listReturnProjetos = new ArrayList<>();
        projetos.forEach(projeto -> {
            listReturnProjetos.add(this.toModelAlocado(projeto, consultor));
        });
        return listReturnProjetos;
    }

    public Projeto toEntityCadastro(ProjetoInputDTO projeto) {
        Projeto projetoEntity = modelMapper.map(projeto, Projeto.class);
        projetoEntity.setStatus(StatusProjeto.NAO_INICIADO);
        projetoEntity.setDataCadastro(LocalDate.now());
        int horasPrevistas = projeto.getConsultores().stream().mapToInt(ProjetoConsultoresInputDTO::getQuantidadeHoras).sum();
        projetoEntity.setHorasPrevistas(horasPrevistas);
        double valor = projeto.getConsultores().stream().mapToDouble(consultor ->
                consultor.getQuantidadeHoras() * consultorRepository.getPrecoHora(consultor.getConsultorId())).sum();
        projetoEntity.setValor(valor);
        return projetoEntity;
    }

    public ProjetoDetalhadoDTO toModelDetalhada(Projeto projeto, List<CCPagantes> ccPagantes) {
        ProjetoDetalhadoDTO projetoDetalhado = modelMapper.map(projeto, ProjetoDetalhadoDTO.class);
        projetoDetalhado.setHorasRestantes(projetoDetalhado.getHorasPrevistas() - projetoDetalhado.getHorasTrabalhadas());
        projetoDetalhado.setValorRestante(projetoDetalhado.getValor() - projetoDetalhado.getValorUtilizado());
        return projetoDetalhado;

    }


}
