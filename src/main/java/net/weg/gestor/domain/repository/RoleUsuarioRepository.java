package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.RoleUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleUsuarioRepository extends JpaRepository<RoleUsuarios, Long> {

    @Query("SELECT p FROM RoleUsuarios p WHERE p.usuarios_id = ?1")
    RoleUsuarios findRoleByIdUsuario(Long usuarios_id);
}
