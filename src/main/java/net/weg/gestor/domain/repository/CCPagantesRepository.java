package net.weg.gestor.domain.repository;

import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.entities.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CCPagantesRepository extends JpaRepository<CCPagantes, Long> {

    @Query("SELECT c FROM CCPagantes c WHERE c.secao = ?1")
    List<CCPagantes> findBySecao(Secao secao);

    @Query("SELECT c FROM CCPagantes c WHERE c.secao = ?1 and c.projeto = ?2")
    List<CCPagantes> findBySecaoAndProjeto(Secao secao, Projeto projeto);

}
