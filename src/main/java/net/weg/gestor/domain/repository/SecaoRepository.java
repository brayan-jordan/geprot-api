package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SecaoRepository extends JpaRepository<Secao, Long> {

    @Query("select s from Secao s where s.id = ?1")
    Secao findByIdAux(long secaoid);

    @Query("SELECT s.verba from Secao s where s.id = ?1")
    Double findByVerba(long secaoId);

    ;



}
