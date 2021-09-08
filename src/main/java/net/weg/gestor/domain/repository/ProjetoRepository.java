package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.StatusProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("select p from Projeto p where p.id = ?1")
    Projeto findByIdProjeto(long id);

    @Query("select p from Projeto p where p.status = ?1")
    List<Projeto> findByStatus(StatusProjeto statusProjeto);

    @Query("SELECT sum(valor) FROM Projeto p where p.id = ?1 ")
    double findVerbaUtilizadaSecao(Long secaoId);



}
