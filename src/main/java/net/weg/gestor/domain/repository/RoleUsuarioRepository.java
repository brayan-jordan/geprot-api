package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.RoleUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUsuarioRepository extends JpaRepository<RoleUsuarios, Long> {
}
