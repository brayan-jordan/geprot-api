package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CCPagantesRepository extends JpaRepository<CCPagantes, Long> {

//    @Query("SELECT c FROM CCPagantes c WHERE c.projetos_id = ?1")
//    List<CCPagantes> findByIdCC(Long ccPaganteId);
//
    @Query("SELECT c FROM CCPagantes c WHERE c.secao = ?1")
    List<CCPagantes> findBySecao(Secao secao);

}
