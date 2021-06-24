package net.weg.gestor.domain.repository;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.CentroDeCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long> {

    @Query("SELECT c FROM CentroDeCusto c WHERE c.codigo = ?1")
    Optional<CentroDeCusto> findById(Long idcentrodecusto);

    @Query("SELECT c FROM CentroDeCusto c WHERE c.codigo = ?1")
    CentroDeCusto findById2(Long idcentrodecusto);

}
