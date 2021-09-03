package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.HorasApontadas;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorasApontadasRepository extends JpaRepository<HorasApontadas, Long> {

    @Query("SELECT h FROM HorasApontadas h WHERE h.projeto = ?1 ORDER BY usuario")
    List<HorasApontadas> findAllInAProject (Projeto projeto);

    @Query("SELECT h FROM HorasApontadas h WHERE h.projeto = ?1 AND h.usuario = ?2 ORDER BY usuario")
    List<HorasApontadas> findAllProjectAndUsuario(Projeto projeto, Usuario usuario);

    @Query("SELECT sum(quantidade_horas) FROM HorasApontadas h WHERE h.projeto = ?1 AND h.usuario = ?2")
    int buscarHoraTotalUser(Projeto projeto, Usuario usuario);

    @Query("SELECT h FROM HorasApontadas h WHERE h.projeto = ?1 AND h.usuario = ?2 AND h.status = ?3")
    List<HorasApontadas> findPendente(Projeto projeto, Usuario usuario, String status);
}
