package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {


    @Query("SELECT g FROM Gestor g where g.usuario.id = ?1")
    Gestor findByUsuarioId(Long usuarios_id);

}
