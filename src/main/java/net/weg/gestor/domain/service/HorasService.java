package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.HorasAssembler;
import net.weg.gestor.domain.entities.HoraApontada;
import net.weg.gestor.domain.repository.ConsultorAlocadoRepository;
import net.weg.gestor.domain.repository.HoraApontadaRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HorasService {

    private HoraApontadaRepository horaApontadaRepository;
    private ConsultorAlocadoRepository consultorAlocadoRepository;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;
    private HorasAssembler horasAssembler;
    private ComponentsService componentsService;

    public List<HoraApontada> listarTodos() {
        return horaApontadaRepository.findAll();
    }

//    public ArrayList<HorasApontadasTotalDTO> getApontamentoTotal(Long projetoId) {
//        List<HoraApontada> thisHorasApontadas = horaApontadaRepository.findTeste(projetoRepository.findByIdProjeto(projetoId));
//        ArrayList<HorasApontadasTotalDTO> listToReturn = new ArrayList<>();
//        for (int i = 0; i < thisHorasApontadas.size(); ++i) {
//            HorasApontadasTotalDTO horaApontada = new HorasApontadasTotalDTO();
//            ConsultorAlocado consultor = consultorAlocadoRepository.buscar
//                    (thisHorasApontadas.get(i).getUsuario().getId(), projetoId);
//            horaApontada.setQuantidade_horas(consultor.getLimiteHoras());
//            horaApontada.setHorasTotais(horaApontadaRepository.buscarHoraTotalUser
//                    (projetoRepository.findByIdProjeto(projetoId),
//                            usuarioRepository.findByIdUsuario(consultor.getConsultores_id())));
//            horaApontada.setNome(usuarioRepository.findByIdUsuario(consultor.getConsultores_id()).getNome());
//            horaApontada.setConsultor_id(consultor.getConsultores_id());
//            if (horaApontadaRepository.findStatus(
//                    projetoRepository.findByIdProjeto(projetoId),
//                    usuarioRepository.findByIdUsuario(consultor.getConsultores_id()),
//                    "PENDENTE").size() > 0) {
//                horaApontada.setStatus("PENDENTE");
//            } else if (horaApontadaRepository.findStatus(
//                    projetoRepository.findByIdProjeto(projetoId),
//                    usuarioRepository.findByIdUsuario(consultor.getConsultores_id()),
//                    "REPROVADO").size() > 0) {
//                horaApontada.setStatus("REPROVADO");
//            } else {
//                horaApontada.setStatus("APROVADO");
//            }
//            listToReturn.add(horaApontada);
//        }
//
//        return listToReturn;
//    }

//    public ListaApontamentoConsultor buscarApontamentoConsultor(Long projetoId, Long usuarioId) {
//        ListaApontamentoConsultor lista = new ListaApontamentoConsultor();
//        Usuario usuario = usuarioRepository.findByIdUsuario(usuarioId);
//        Projeto projeto = projetoRepository.findByIdProjeto(projetoId);
//        lista.setTodosApontamentos(horasAssembler.toCollectionModel(
//                componentsService.buscarHorasPorStatus(projetoId, usuarioId, "APROVADO")));
//        List<ColunaHoraApontadaDTO> listToUse = horasAssembler.toCollectionModel(
//                componentsService.buscarHorasPorStatus(projetoId, usuarioId, "REPROVADO"));
//        List<ColunaHoraApontadaDTO> listToUse2 = horasAssembler.toCollectionModel(
//                componentsService.buscarHorasPorStatus(projetoId, usuarioId, "PENDENTE"));
//        for (int i = 0; i < listToUse.size(); ++i) {
//            lista.getTodosApontamentos().add(listToUse.get(0));
//        }
//        for (int i = 0; i < listToUse2.size(); ++i) {
//            lista.getTodosApontamentos().add(listToUse2.get(0));
//        }
//        lista.setTotalHoras(horaApontadaRepository.buscarHoraTotalUser(
//                projetoRepository.findByIdProjeto(projetoId), usuarioRepository.findByIdUsuario(usuarioId)));
//        lista.setValorGasto(lista.getTotalHoras() * usuarioRepository.findByIdUsuario(usuarioId).getPrecoHora());
//        lista.setNome(usuarioRepository.findByIdUsuario(usuarioId).getNome());
//        lista.setStatusTotal("POSSIVEL");
//        // verificacoes para retornar para fazer o botao se tem horas possiveis para aprovar
//        // nada que um // gambiarra nao resolva
//        for(int i = 0; i < lista.getTodosApontamentos().size(); ++i) {
//            if (lista.getTodosApontamentos().get(i).getStatus().equals("REPROVADO")) {
//                lista.setStatusTotal("NAO_POSSIVEL");
//            }
//        }
//        if (horaApontadaRepository.findStatus(projetoRepository.findByIdProjeto(projetoId),
//                usuarioRepository.findByIdUsuario(usuarioId), "APROVADO").size() == lista.getTodosApontamentos().size()) {
//            lista.setStatusTotal("NAO_POSSIVEL");
//        }
//
//        return lista;
//    }
//
//    public String aprovarApontamentosConsultor(Long projetoId, Long usuarioId) {
//        List<HoraApontada> horasApontadas = componentsService.buscarHorasPorStatus(projetoId, usuarioId, "PENDENTE");
//        if (horasApontadas.size() == 0) {
//            throw new NegocioException("Esse consultor já está com todas suas horas aprovadas");
//        }
//        for (HoraApontada horasApontada : horasApontadas) {
//            horasApontada.setStatus("APROVADO");
//        }
//        horaApontadaRepository.saveAll(horasApontadas);
//        return "Horas aprovadas com sucesso";
//    }
//
//    public String reprovarApontamentosConsultor(Long projetoId, Long usuarioId) {
//        List<HoraApontada> horasApontadas = componentsService.buscarHorasPorStatus(projetoId, usuarioId, "PENDENTE");
//        if (horasApontadas.size() == 0) {
//            throw new NegocioException("Esse consultor não tem horas pendentes para serem reprovadas");
//        }
//        for (HoraApontada horasApontada : horasApontadas) {
//            horasApontada.setStatus("REPROVADO");
//        }
//        horaApontadaRepository.saveAll(horasApontadas);
//        return "Horas reprovadas com sucesso";
//    }
//
//    public String apontarHoras(ApontamentoDeHoraInputDTO apontamento) {
//        long consultorId = apontamento.getUsuarios_id();
//        long projetoId = apontamento.getProjetos_id();
//        if (consultorAlocadoRepository.existsVerify(consultorId, projetoId).isEmpty()) {
//            throw new NegocioException("Esse consultor não está alocado nesse projeto, tente novamente");
//        }
//
//        ConsultorAlocado alocado = consultorAlocadoRepository.buscar(consultorId, projetoId);
//
//        if ((apontamento.getQuantidade_horas() + alocado.getHorasApontadas()) > alocado.getLimiteHoras()) {
//            throw new NegocioException("Voce esta tentando apontar mais horas que o seu limite!");
//        }
//
//        Projeto projeto = projetoRepository.findByIdProjeto(projetoId);
//        Usuario consultor = usuarioRepository.findByIdUsuario(consultorId);
//
//        if (horaApontadaRepository.findStatus(projeto, consultor, "REPROVADO").size() > 0) {
//            throw new NegocioException("Ajuste primeiramente suas horas reprovadas para conseguir apontar novamente");
//        }
//
//        HoraApontada horaApontada = horasAssembler.toEntity(apontamento);
//        horaApontada.setData(LocalDate.now());
//        horaApontada.setUsuario(consultor);
//        horaApontada.setProjeto(projeto);
//        horaApontada.setStatus("PENDENTE");
//        horaApontadaRepository.save(horaApontada);
//        ConsultorAlocado consultoresAlocados = consultorAlocadoRepository.buscar(consultorId, projetoId);
//        consultoresAlocados.setHorasApontadas(consultoresAlocados.getHorasApontadas() + apontamento.getQuantidade_horas());
//        consultorAlocadoRepository.save(consultoresAlocados);
//        Projeto projetoEdit = projetoRepository.findByIdProjeto(projetoId);
//        projeto.setHorasTrabalhadas(projeto.getHorasTrabalhadas() + horaApontada.getQuantidade_horas());
//        projeto.setValorUtilizado(
//                projeto.getValorUtilizado() + consultor.getPrecoHora() * apontamento.getQuantidade_horas());
//        projetoRepository.save(projeto);
//        return "Hora apontada com sucesso";
//    }

}
