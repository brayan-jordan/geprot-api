package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.Secao;
import net.weg.gestor.domain.model.StatusProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("select p from Projeto p where p.id = ?1")
    Projeto findByIdProjeto(long id);

    @Query("select p from Projeto p where p.id = ?1 and p.dataFinalizacao = ?2")
    Projeto findByIdAndDate(long id, LocalDate data);

    @Query("select p from Projeto p where p.status = ?1")
    List<Projeto> findByStatus(StatusProjeto statusProjeto);

    @Query("select p from Projeto p where p.status = ?1 and p.id = ?2")
    Projeto findByStatusSecao(StatusProjeto statusProjeto, Long Id);

    @Query("SELECT sum(valor) FROM Projeto p where p.id = ?1 ")
    double findVerbaUtilizadaSecao(Long secaoId);

}
