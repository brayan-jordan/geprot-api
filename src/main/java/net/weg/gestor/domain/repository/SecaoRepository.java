package net.weg.gestor.domain.repository;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import net.weg.gestor.domain.model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecaoRepository extends JpaRepository<Secao, Long> {

    @Query("select p from Secao p where p.idsecao = ?1")
    Secao findById2(long secaoid);

    Optional<Secao> findById(long secaoid);

}
