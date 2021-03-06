package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultorAssembler;
import net.weg.gestor.api.map.HorasAssembler;
import net.weg.gestor.api.model.apontarhora.ApontarHoraInputDTO;
import net.weg.gestor.api.model.apontarhora.MotivoReprovacao;
import net.weg.gestor.api.model.consultor.ConsultorAlocadoDTO;
import net.weg.gestor.api.model.consultorhoras.ConsultorComSuasHorasApontadas;
import net.weg.gestor.api.model.consultorhoras.HoraApontadaDTO;
import net.weg.gestor.domain.entities.*;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static net.weg.gestor.domain.entities.StatusApontamento.REPROVADO;

@Service
@AllArgsConstructor
public class HorasService {

    private HoraApontadaRepository horaApontadaRepository;
    private ConsultorAlocadoRepository consultorAlocadoRepository;
    private ConsultorAssembler consultorAssembler;
    private ConsultorRepository consultorRepository;
    private ProjetoRepository projetoRepository;
    private HorasAssembler horasAssembler;

    public List<HoraApontada> listarTodos() {
        return horaApontadaRepository.findAll();
    }

    public List<ConsultorAlocadoDTO> buscarConsultoresAlocadosProjeto(Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new NegocioException("a"));
//        Busca todos os consultores alocados do projeto desejado, e já os transforma no formato de classe de retorno
//        para depois apenas fazer as verificações e retornar os dados
        List<ConsultorAlocadoDTO> consultores = consultorAssembler.toCollectionModelAlocado(
                consultorAlocadoRepository.consultoresAlocadosProjeto(projeto)
        );

//        Já nessa parte o status dos apontamentos precisam ter apenas 1 status, então
//        segundo a regra de negócio caso ele tenha alguma hora reprovada não terá pendentes, mantendo
//        seu status como reprovado, caso tenha pendente não terá reprovada, mantendo seu status como pendente
//        e por fim caso não contenha nem pendentes nem reprovadas estará com todas suas horas aprovadas
        consultores.forEach(consultor -> {
            if (horaApontadaRepository.buscarHorasReprovadasConsultor(
                consultorRepository.getById(consultor.getId())).size() > 0)
            {
                consultor.setStatusApontamento(REPROVADO);
            }
            else if (horaApontadaRepository.buscarHorasPendentesConsultor(
                    consultorRepository.getById(consultor.getId())).size() > 0)
            {
                consultor.setStatusApontamento(StatusApontamento.PENDENTE);
            }
            else {
                consultor.setStatusApontamento(StatusApontamento.APROVADO);
            }
        });

        return consultores;
    }

    public ConsultorComSuasHorasApontadas buscarHorasConsultor(Long projetoId, Long consultorId) {
        Consultor consultor = consultorRepository.findById(consultorId).
                orElseThrow(() -> new NegocioException("Consultor nao encontrado")
        );

        Projeto projeto = projetoRepository.findById(projetoId).
                orElseThrow(() -> new NegocioException("Projeto nao encontrado")
        );

        ConsultorComSuasHorasApontadas consultorComSuasHorasApontadas = consultorAssembler.toModelConsultorAndHoras(consultor);
        consultorComSuasHorasApontadas.setHoras(buscarHorasConsultorAndProjeto(projeto, consultor));
        calcularHorasTotaisAndValorGasto(consultorComSuasHorasApontadas);
        ConsultorAlocado consultorAlocado = consultorAlocadoRepository.buscarConsultorAlocadoEmProjeto(consultor, projeto);
        consultorComSuasHorasApontadas.setPodeApontar(verificaSePodeAprovar(consultor, projeto));
        return consultorComSuasHorasApontadas;
    }

    private List<HoraApontadaDTO> buscarHorasConsultorAndProjeto(Projeto projeto, Consultor consultor) {
        ArrayList<HoraApontadaDTO> todasHorasConsultor = new ArrayList<>(horasAssembler.toCollectionModel(
                horaApontadaRepository.buscarHorasAprovadasConsultorAndProjeto(consultor, projeto)));

        todasHorasConsultor.addAll(horasAssembler.toCollectionModel(
                horaApontadaRepository.buscarHorasPendentesConsultorAndProjeto(consultor, projeto)));

        todasHorasConsultor.addAll(horasAssembler.toCollectionModel(
                horaApontadaRepository.buscarHorasReprovadasConsultorAndProjeto(consultor, projeto)));

        return todasHorasConsultor;
    }

    private void calcularHorasTotaisAndValorGasto (ConsultorComSuasHorasApontadas consultorComSuasHorasApontadas) {
        consultorComSuasHorasApontadas.getHoras().forEach(horaApontadaDTO -> {
            consultorComSuasHorasApontadas.setHorasTotais(consultorComSuasHorasApontadas.getHorasTotais() + horaApontadaDTO.getQuantidadeHoras());
        });

        consultorComSuasHorasApontadas.setTotalGasto(consultorComSuasHorasApontadas.getHorasTotais() * consultorComSuasHorasApontadas.getPrecoHora());
    }

    public String aprovarHoras(Long projetoId, Long consultorId) {
        Consultor consultor = consultorRepository.findById(consultorId).
                orElseThrow(() -> new NegocioException("Consultor nao encontrado"));

        Projeto projeto = projetoRepository.findById(projetoId).
                orElseThrow(() -> new NegocioException("Projeto nao encontrado"));

        if (horaApontadaRepository.buscarHorasPendentesConsultorAndProjeto(consultor, projeto).size() == 0) {
            throw new NegocioException("Esse consultor não tem horas para serem avaliadas");
        }

        List<HoraApontada> horaApontadas = horaApontadaRepository.buscarHorasPendentesConsultorAndProjeto(consultor, projeto);

        horaApontadas.forEach(horaApontada -> {
            horaApontada.setStatus(StatusApontamento.APROVADO);
        });

        horaApontadaRepository.saveAll(horaApontadas);

        if (horaApontadaRepository.buscarHorasReprovadasProjeto(projeto).size() == 0
            && horaApontadaRepository.buscarHorasPendentesProjeto(projeto).size() == 0
            && projeto.getHorasPrevistas() == projeto.getHorasTrabalhadas()
        ) {

            projeto.setStatus(StatusProjeto.CONCLUIDO);
            projeto.setDataFinalizacao(LocalDate.now());
        }

        projetoRepository.save(projeto);

        return "Deu boa";
    }

    public String reprovarHoras(Long projetoId, Long consultorId, MotivoReprovacao motivoReprovacao) {
        Consultor consultor = consultorRepository.findById(consultorId).
                orElseThrow(() -> new NegocioException("Consultor nao encontrado"));

        Projeto projeto = projetoRepository.findById(projetoId).
                orElseThrow(() -> new NegocioException("Projeto nao encontrado"));

        if (horaApontadaRepository.buscarHorasPendentesConsultorAndProjeto(consultor, projeto).size() == 0) {
            throw new NegocioException("Esse consultor não tem horas para serem avaliadas");
        }

        List<HoraApontada> horaApontadas = horaApontadaRepository.buscarHorasPendentesConsultorAndProjeto(consultor, projeto);

        horaApontadas.forEach(horaApontada -> {
            horaApontada.setStatus(REPROVADO);
            horaApontada.setMotivoReprovacao(motivoReprovacao.getMotivo());
        });

        horaApontadaRepository.saveAll(horaApontadas);

        return "Deu boa";
    }

    public String apontarHoras(ApontarHoraInputDTO infoHoraApontada) {
        Consultor consultor = consultorRepository.findById(infoHoraApontada.getConsultorId()).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado com esse ID")
        );

        Projeto projeto = projetoRepository.findById(infoHoraApontada.getProjetoId()).orElseThrow(
                () -> new NegocioException("Projeto nao encontrado com esse ID")
        );

        if (consultorAlocadoRepository.verificaSeConsultorEstaAlocado(consultor, projeto).isEmpty()) {
            throw new NegocioException("Voce nao esta alocado nesse projeto para apontar horas");
        }

        ConsultorAlocado consultorAlocado = consultorAlocadoRepository.buscarConsultorAlocadoEmProjeto(consultor, projeto);

        if (consultorAlocado.getHorasApontadas() + infoHoraApontada.getQuantidadeHoras() > consultorAlocado.getLimiteHoras()) {
            throw new NegocioException("Voce nao tem mais esse tanto de horas disponiveis para apontar");
        }

        horaApontadaRepository.save(new HoraApontada(
                projeto, infoHoraApontada.getQuantidadeHoras(),
                LocalDate.now(), consultor, infoHoraApontada.getDescricaoApontamento(),
                StatusApontamento.PENDENTE)
        );

        consultorAlocado.setHorasApontadas(consultorAlocado.getHorasApontadas() + infoHoraApontada.getQuantidadeHoras());
        projeto.setValorUtilizado(projeto.getValorUtilizado() + (infoHoraApontada.getQuantidadeHoras() * consultor.getPrecoHora()));
        projeto.setHorasTrabalhadas(projeto.getHorasTrabalhadas() + infoHoraApontada.getQuantidadeHoras());

        if (projeto.getStatus().equals(StatusProjeto.NAO_INICIADO)) {
            projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
            projeto.setDataInicio(LocalDate.now());
        }

        projetoRepository.save(projeto);
        consultorAlocadoRepository.save(consultorAlocado);

        return "Hora apontada com sucesso";

    }

    private boolean verificaSePodeAprovar(Consultor consultor, Projeto projeto) {

        return horaApontadaRepository.buscarHorasReprovadasConsultorAndProjeto(consultor, projeto).size() == 0
                && horaApontadaRepository.buscarHorasPendentesConsultorAndProjeto(consultor, projeto).size() > 0;
    }
}
