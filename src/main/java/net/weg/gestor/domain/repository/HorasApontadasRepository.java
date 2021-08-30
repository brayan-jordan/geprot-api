package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.HorasApontadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorasApontadasRepository extends JpaRepository<HorasApontadas, Long> {
}
