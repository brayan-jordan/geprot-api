package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Gestor;
import net.weg.gestor.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {

    @Query("SELECT g FROM Gestor g where g.usuario = ?1")
    Gestor findByUsuarioId(Usuario usuario);

}
