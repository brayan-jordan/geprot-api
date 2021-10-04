package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.Consultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorRepository extends JpaRepository<Consultor, Long> {


}
