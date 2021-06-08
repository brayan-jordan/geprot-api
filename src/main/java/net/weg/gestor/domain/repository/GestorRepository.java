package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {

    Optional<Gestor> findByEmail(String email);

    Optional<Gestor> findByidGestor(long idGestor);

}
