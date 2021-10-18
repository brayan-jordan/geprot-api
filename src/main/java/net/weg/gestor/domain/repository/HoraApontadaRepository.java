package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.HoraApontada;
import net.weg.gestor.domain.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoraApontadaRepository extends JpaRepository<HoraApontada, Long> {

    @Query("SELECT h FROM HoraApontada h where h.consultor = ?1 and h.status = 2")
    List<HoraApontada> buscarHorasPendentesConsultor(Long consultorId);

    @Query("SELECT h FROM HoraApontada h where h.consultor = ?1 and h.status = 0")
    List<HoraApontada> buscarHorasAprovadasConsultor(Long consultorId);

    @Query("SELECT h FROM HoraApontada h where h.consultor = ?1 and h.status = 1")
    List<HoraApontada> buscarHorasReprovadasConsultor(Long consultorId);

}
