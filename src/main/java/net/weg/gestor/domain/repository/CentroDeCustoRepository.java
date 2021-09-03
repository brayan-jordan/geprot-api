package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.CentroDeCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long> {

    @Query("SELECT c FROM CentroDeCusto c WHERE c.id = ?1")
    Optional<CentroDeCusto> findById(Long idcentrodecusto);

    @Query("SELECT c from CentroDeCusto c WHERE c.id = ?1")
    CentroDeCusto buscar(Long id);

}
