package net.weg.gestor.domain.repository;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query("SELECT s FROM Skill s where s.id = ?1")
    Skill buscarPorId(Long id);

}
