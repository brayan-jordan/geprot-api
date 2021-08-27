package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.RoleUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleUsuarioRepository extends JpaRepository<RoleUsuarios, Long> {

    @Query("SELECT p FROM RoleUsuarios p WHERE p.id_usuarios = ?1")
    Optional<RoleUsuarios> findRoleById(Long id_usuarios);

    @Query("SELECT p FROM RoleUsuarios p WHERE p.id_usuarios = ?1")
    RoleUsuarios findRoleByIdUsuario(Long id_usuarios);
}
