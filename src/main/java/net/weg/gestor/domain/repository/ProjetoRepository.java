package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.entities.StatusProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p From Projeto p where p.dataFinalizacao = ?1")
    List<Projeto> contarProjetosConcluidosPorDia(LocalDate date);

}
