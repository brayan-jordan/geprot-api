package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("select p from Projeto p where p.nomeprojeto = ?1 ")
    Optional<Projeto> findByNomeprojeto(String nome);

    @Query("select p from Projeto p where p.idprojeto = ?1")
    Optional<Projeto> findByidProjeto(long id);


}
