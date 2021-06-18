package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {

    @Query("select g from Gestor g where g.email = ?1")
    Optional<Gestor> findByEmail(String email);

    Optional<Gestor> findByidgestor(long idgestor);

    @Query("select g from Gestor g where g.idgestor = ?1")
    Gestor findByidgestor2(long idgestor);

}
