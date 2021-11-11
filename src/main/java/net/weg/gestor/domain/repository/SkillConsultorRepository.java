package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.SkillConsultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillConsultorRepository extends JpaRepository<SkillConsultor, Long> {
}
