package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.HorasAssembler;
import net.weg.gestor.api.model.HorasApontadasTotalDTO;
import net.weg.gestor.api.model.ListaApontamentoConsultor;
import net.weg.gestor.api.model.apontarinputDTO.ApontamentoDeHoraInputDTO;
import net.weg.gestor.domain.model.ConsultoresAlocados;
import net.weg.gestor.domain.model.HorasApontadas;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
import net.weg.gestor.domain.repository.HorasApontadasRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HorasService {

    private HorasApontadasRepository horasApontadasRepository;
    private ConsultoresAlocadosRepository consultoresAlocadosRepository;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;
    private HorasAssembler horasAssembler;

    public List<HorasApontadas> listarTodos() {
        return horasApontadasRepository.findAll();
    }

    public ArrayList<HorasApontadasTotalDTO> apontamentoTotal(Long projetoId) {
        Projeto projeto = projetoRepository.findByIdProjeto(projetoId);
        List<HorasApontadas> horasDoProjeto = horasApontadasRepository.findAllInAProject(projeto);
        return convert(horasDoProjeto);
    }

    public ArrayList<HorasApontadasTotalDTO> convert(List<HorasApontadas> apontadas) {
        ArrayList<HorasApontadasTotalDTO> horasTotais = new ArrayList<HorasApontadasTotalDTO>();
        long ultimaId = 0;
        for (int i = 0; i < apontadas.size(); ++i) {
            if (!(ultimaId == apontadas.get(i).getUsuario().getId())) {
                List<HorasApontadas> porUsuario = horasApontadasRepository.findAllProjectAndUsuario(
                        projetoRepository.findByIdProjeto(apontadas.get(i).getProjeto().getId()),
                        usuarioRepository.findByIdUsuario(apontadas.get(i).getUsuario().getId()));
                HorasApontadasTotalDTO horasApontadasTotalDTO = new HorasApontadasTotalDTO();
                horasApontadasTotalDTO.setConsultor_id(porUsuario.get(0).getUsuario().getId());
                horasApontadasTotalDTO.setStatus(porUsuario.get(0).getStatus());
                horasApontadasTotalDTO.setNome(porUsuario.get(0).getUsuario().getNome());
                horasApontadasTotalDTO.setHorasTotais(horasApontadasRepository.buscarHoraTotalUser(
                        projetoRepository.findByIdProjeto(apontadas.get(i).getProjeto().getId()),
                        usuarioRepository.findByIdUsuario(apontadas.get(i).getUsuario().getId())));
                if (horasApontadasRepository.findPendente(
                        projetoRepository.findByIdProjeto(apontadas.get(i).getProjeto().getId()),
                        usuarioRepository.findByIdUsuario(apontadas.get(i).getUsuario().getId()),
                        "PENDENTE").size() > 0) {

                    horasApontadasTotalDTO.setStatus("PENDENTE");
                }
                ultimaId = horasApontadasTotalDTO.getConsultor_id();
                horasTotais.add(horasApontadasTotalDTO);
            }
        }
        return horasTotais;

    }

    public ListaApontamentoConsultor buscarApontamentoConsultor(Long projetoId, Long usuarioId) {
        ListaApontamentoConsultor lista = new ListaApontamentoConsultor();
        lista.setTodosApontamentos(horasAssembler.toCollectionModel(horasApontadasRepository.findAllProjectAndUsuario(
                projetoRepository.findByIdProjeto(projetoId), usuarioRepository.findByIdUsuario(usuarioId))));
        lista.setTotalHoras(horasApontadasRepository.buscarHoraTotalUser(
                projetoRepository.findByIdProjeto(projetoId), usuarioRepository.findByIdUsuario(usuarioId)));
        lista.setValorGasto(lista.getTotalHoras() * 200.50);
        return lista;
    }

    public String aprovarApontamentosConsultor(Long projetoId, Long usuarioId) {
        List<HorasApontadas> horasApontadas = horasApontadasRepository.findPendente(
                projetoRepository.findByIdProjeto(projetoId),
                usuarioRepository.findByIdUsuario(usuarioId),
                "PENDENTE"
        );
        for (int i = 0; i < horasApontadas.size(); ++i) {
            horasApontadas.get(i).setStatus("APROVADO");
        }
        horasApontadasRepository.saveAll(horasApontadas);
        return "Horas aprovadas com sucesso";
    }

    public String reprovarApontamentosConsultor(Long projetoId, Long usuarioId) {
        List<HorasApontadas> horasApontadas = horasApontadasRepository.findPendente(
                projetoRepository.findByIdProjeto(projetoId),
                usuarioRepository.findByIdUsuario(usuarioId),
                "PENDENTE"
        );
        for (int i = 0; i < horasApontadas.size(); ++i) {
            horasApontadas.get(i).setStatus("REPROVADO");
        }
        horasApontadasRepository.saveAll(horasApontadas);
        return "Horas reprovadas com sucesso";
    }

    public String apontarHoras(ApontamentoDeHoraInputDTO apontamento) {
        HorasApontadas horaApontada = horasAssembler.toEntity(apontamento);
        horaApontada.setData(LocalDate.now());
        horaApontada.setUsuario(usuarioRepository.findByIdUsuario(apontamento.getUsuarios_id()));
        horaApontada.setProjeto(projetoRepository.findByIdProjeto(apontamento.getProjetos_id()));
        horaApontada.setStatus("PENDENTE");
        horasApontadasRepository.save(horaApontada);
        Projeto projeto = projetoRepository.findByIdProjeto(apontamento.getProjetos_id());
        projeto.setHorasTrabalhadas(projeto.getHorasTrabalhadas() + horaApontada.getQuantidade_horas());
        projetoRepository.save(projeto);
        ConsultoresAlocados consultoresAlocados = consultoresAlocadosRepository.findByIdConsultor(apontamento.getUsuarios_id());
        consultoresAlocados.setHorasApontadas(consultoresAlocados.getHorasApontadas() + apontamento.getQuantidade_horas());
        consultoresAlocadosRepository.save(consultoresAlocados);
        return "Hora apontada com sucesso";
    }

}
