package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.HoraApontada;
import net.weg.gestor.domain.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoraApontadaRepository extends JpaRepository<HoraApontada, Long> {

}
