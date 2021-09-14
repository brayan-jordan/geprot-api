package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.id = ?1")
    Usuario findByIdUsuario(long id);

    @Query("select u from Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.precoHora > 0")
    List<Usuario> findConsultores();

    @Query("SELECT u FROM Usuario u WHERE u.precoHora > 0 and u.id = ?1")
    Usuario findConsultor(Long usuarioId);


}
