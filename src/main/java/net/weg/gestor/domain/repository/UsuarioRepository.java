package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByidgestor(long idgestor);

    @Query("select g from Gestor g where g.idgestor = ?1")
    Usuario findByidgestor2(long idgestor);

    @Query("select u from Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String email);


}
