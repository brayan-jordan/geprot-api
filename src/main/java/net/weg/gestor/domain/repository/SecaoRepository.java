package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SecaoRepository extends JpaRepository<Secao, Long> {

    @Query("select p from Secao p where p.id = ?1")
    Secao findByIdAux(long secaoid);


}
