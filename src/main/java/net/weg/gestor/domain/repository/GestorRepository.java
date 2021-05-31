package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {

    List<Gestor> findById(long id);

}
