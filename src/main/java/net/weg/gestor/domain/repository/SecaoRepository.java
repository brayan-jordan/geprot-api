package net.weg.gestor.domain.repository;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import net.weg.gestor.domain.model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecaoRepository extends JpaRepository<Secao, Long> {

    Optional<Secao> findById(long secaoId);

}
