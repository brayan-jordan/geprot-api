package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.CCPagantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CCPagantesRepository extends JpaRepository<CCPagantes, Long> {

//    @Query("SELECT c FROM CCPagantes c WHERE c.projetos_id = ?1")
//    List<CCPagantes> findByIdCC(Long ccPaganteId);
//
//    @Query("SELECT c FROM CCPagantes c WHERE c.secoes_id = ?1")
//    List<CCPagantes> findByIdSecao(Long secoes_id);

}
