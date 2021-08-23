package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.CCPagantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CCPagantesRepository extends JpaRepository<CCPagantes, Long> {

    @Query("SELECT c FROM CCPagantes c where c.projeto.id = ?1")
    List<CCPagantes> findByIdProjeto(Long projetoid);

}
