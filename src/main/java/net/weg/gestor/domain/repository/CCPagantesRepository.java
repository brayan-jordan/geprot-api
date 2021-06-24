package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.CCPagantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CCPagantesRepository extends JpaRepository<CCPagantes, Long> {

    

}
