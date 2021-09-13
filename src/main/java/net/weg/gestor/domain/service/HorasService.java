package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.HorasAssembler;
import net.weg.gestor.api.model.ColunaHoraApontadaDTO;
import net.weg.gestor.api.model.HorasApontadasTotalDTO;
import net.weg.gestor.api.model.ListaApontamentoConsultor;
import net.weg.gestor.api.model.apontarinputDTO.ApontamentoDeHoraInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
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

    public ArrayList<HorasApontadasTotalDTO> getApontamentoTotal(Long projetoId) {
        List<HorasApontadas> thisHorasApontadas = horasApontadasRepository.findTeste(projetoRepository.findByIdProjeto(projetoId));
        ArrayList<HorasApontadasTotalDTO> listToReturn = new ArrayList<>();
        for (int i = 0; i < thisHorasApontadas.size(); ++i) {
            HorasApontadasTotalDTO horaApontada = new HorasApontadasTotalDTO();
            ConsultoresAlocados consultor = consultoresAlocadosRepository.buscar
                    (thisHorasApontadas.get(i).getUsuario().getId(), projetoId);
            horaApontada.setQuantidade_horas(consultor.getLimiteHoras());
            horaApontada.setHorasTotais(horasApontadasRepository.buscarHoraTotalUser
                    (projetoRepository.findByIdProjeto(projetoId),
                            usuarioRepository.findByIdUsuario(consultor.getUsuarios_id())));
            horaApontada.setNome(usuarioRepository.findByIdUsuario(consultor.getUsuarios_id()).getNome());
            horaApontada.setConsultor_id(consultor.getUsuarios_id());
            if (horasApontadasRepository.findStatus(
                    projetoRepository.findByIdProjeto(projetoId),
                    usuarioRepository.findByIdUsuario(consultor.getUsuarios_id()),
                    "PENDENTE").size() > 0) {
                horaApontada.setStatus("PENDENTE");
            } else if (horasApontadasRepository.findStatus(
                    projetoRepository.findByIdProjeto(projetoId),
                    usuarioRepository.findByIdUsuario(consultor.getUsuarios_id()),
                    "REPROVADO").size() > 0) {
                horaApontada.setStatus("REPROVADO");
            } else {
                horaApontada.setStatus("APROVADO");
            }
            listToReturn.add(horaApontada);
        }

        return listToReturn;
    }

    public ListaApontamentoConsultor buscarApontamentoConsultor(Long projetoId, Long usuarioId) {
        ListaApontamentoConsultor lista = new ListaApontamentoConsultor();
        lista.setTodosApontamentos(horasAssembler.toCollectionModel(horasApontadasRepository.findStatus(
                projetoRepository.findByIdProjeto(projetoId), usuarioRepository.findByIdUsuario(usuarioId), "APROVADO")));
        List<ColunaHoraApontadaDTO> listToUse = horasAssembler.toCollectionModel(horasApontadasRepository.findStatus(
                projetoRepository.findByIdProjeto(projetoId), usuarioRepository.findByIdUsuario(usuarioId), "REPROVADO"));
        List<ColunaHoraApontadaDTO> listToUse2 = horasAssembler.toCollectionModel(horasApontadasRepository.findStatus(
                projetoRepository.findByIdProjeto(projetoId), usuarioRepository.findByIdUsuario(usuarioId), "PENDENTE"));
        for (int i = 0; i < listToUse.size(); ++i) {
            lista.getTodosApontamentos().add(listToUse.get(0));
        }
        for (int i = 0; i < listToUse2.size(); ++i) {
            lista.getTodosApontamentos().add(listToUse2.get(0));
        }
        lista.setTotalHoras(horasApontadasRepository.buscarHoraTotalUser(
                projetoRepository.findByIdProjeto(projetoId), usuarioRepository.findByIdUsuario(usuarioId)));
        lista.setValorGasto(lista.getTotalHoras() * usuarioRepository.findByIdUsuario(usuarioId).getPrecoHora());
        lista.setNome(usuarioRepository.findByIdUsuario(usuarioId).getNome());
        return lista;
    }

    public String aprovarApontamentosConsultor(Long projetoId, Long usuarioId) {
        List<HorasApontadas> horasApontadas = horasApontadasRepository.findStatus(
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
        List<HorasApontadas> horasApontadas = horasApontadasRepository.findStatus(
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
        if (consultoresAlocadosRepository.existsVerify(
                apontamento.getUsuarios_id(), apontamento.getProjetos_id()).isEmpty()) {
            throw new NegocioException("Esse consultor não está alocado nesse projeto, tente novamente");
        }

        ConsultoresAlocados alocado = consultoresAlocadosRepository.buscar(
                apontamento.getUsuarios_id(), apontamento.getProjetos_id());

        if ((apontamento.getQuantidade_horas() + alocado.getHorasApontadas()) > alocado.getLimiteHoras()) {
            throw new NegocioException("Voce esta tentando apontar mais horas que o seu limite!");
        }

        if (horasApontadasRepository.findAllProjectAndUsuario(
                projetoRepository.findByIdProjeto(apontamento.getProjetos_id()),
                usuarioRepository.findByIdUsuario(apontamento.getUsuarios_id())).size() > 0) {
            if (horasApontadasRepository.findAllProjectAndUsuario(
                    projetoRepository.findByIdProjeto(apontamento.getProjetos_id()),
                    usuarioRepository.findByIdUsuario(apontamento.getUsuarios_id())).
                    get(0).getStatus().equals("REPROVADO")) {
                throw new NegocioException("Voce tem horas recusada");
            }
        }

        HorasApontadas horaApontada = horasAssembler.toEntity(apontamento);
        horaApontada.setData(LocalDate.now());
        horaApontada.setUsuario(usuarioRepository.findByIdUsuario(apontamento.getUsuarios_id()));
        horaApontada.setProjeto(projetoRepository.findByIdProjeto(apontamento.getProjetos_id()));
        horaApontada.setStatus("PENDENTE");
        horasApontadasRepository.save(horaApontada);
        ConsultoresAlocados consultoresAlocados = consultoresAlocadosRepository.findByIdConsultor(apontamento.getUsuarios_id());
        consultoresAlocados.setHorasApontadas(consultoresAlocados.getHorasApontadas() + apontamento.getQuantidade_horas());
        consultoresAlocadosRepository.save(consultoresAlocados);
        Projeto projeto = projetoRepository.findByIdProjeto(apontamento.getProjetos_id());
        projeto.setHorasTrabalhadas(projeto.getHorasTrabalhadas() + horaApontada.getQuantidade_horas());
        projeto.setValorUtilizado(projeto.getValorUtilizado() +
                usuarioRepository.findByIdUsuario(apontamento.getUsuarios_id()).getPrecoHora() *
                apontamento.getQuantidade_horas());
        projetoRepository.save(projeto);
        return "Hora apontada com sucesso";
    }

}
