package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

//    @Query("SELECT n FROM Notificacao n where n.usuario = ?1")
//    List<Notificacao> buscarTodasPorUsuario(Usuario usuario);
//
//    @Query("SELECT n FROM Notificacao n where n.usuario = ?1 and n.statusLeitura = false")
//    List<Notificacao> buscarQuantidadeNaoLida(Usuario usuario);

}
