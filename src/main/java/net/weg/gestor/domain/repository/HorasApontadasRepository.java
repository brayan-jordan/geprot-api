package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.HorasApontadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorasApontadasRepository extends JpaRepository<HorasApontadas, Long> {

//    @Query("SELECT h FROM HorasApontadas h WHERE h.projeto = ?1 ORDER BY usuario")
//    List<HorasApontadas> findAllInAProject (Projeto projeto);
//
//    @Query("SELECT h FROM HorasApontadas h WHERE h.projeto = ?1 GROUP BY usuario")
//    List<HorasApontadas> findTeste (Projeto projeto);
//
//    @Query("SELECT h FROM HorasApontadas h WHERE h.projeto = ?1 AND h.usuario = ?2 ORDER BY usuario")
//    List<HorasApontadas> findAllProjectAndUsuario(Projeto projeto, Usuario usuario);
//
//    @Query("SELECT sum(quantidade_horas) FROM HorasApontadas h WHERE h.projeto = ?1 AND h.usuario = ?2")
//    int buscarHoraTotalUser(Projeto projeto, Usuario usuario);
//
//    @Query("SELECT h FROM HorasApontadas h WHERE h.projeto = ?1 AND h.usuario = ?2 AND h.status = ?3")
//    List<HorasApontadas> findStatus(Projeto projeto, Usuario usuario, String status);

}
