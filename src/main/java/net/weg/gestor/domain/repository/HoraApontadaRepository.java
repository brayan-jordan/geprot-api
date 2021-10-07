package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.HoraApontada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoraApontadaRepository extends JpaRepository<HoraApontada, Long> {

//    @Query("SELECT h FROM HoraApontada h WHERE h.projeto = ?1 ORDER BY usuario")
//    List<HoraApontada> findAllInAProject (Projeto projeto);
//
//    @Query("SELECT h FROM HoraApontada h WHERE h.projeto = ?1 GROUP BY usuario")
//    List<HoraApontada> findTeste (Projeto projeto);
//
//    @Query("SELECT h FROM HoraApontada h WHERE h.projeto = ?1 AND h.usuario = ?2 ORDER BY usuario")
//    List<HoraApontada> findAllProjectAndUsuario(Projeto projeto, Usuario usuario);
//
//    @Query("SELECT sum(quantidade_horas) FROM HoraApontada h WHERE h.projeto = ?1 AND h.usuario = ?2")
//    int buscarHoraTotalUser(Projeto projeto, Usuario usuario);
//
//    @Query("SELECT h FROM HoraApontada h WHERE h.projeto = ?1 AND h.usuario = ?2 AND h.status = ?3")
//    List<HoraApontada> findStatus(Projeto projeto, Usuario usuario, String status);

}
