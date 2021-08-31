package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.ConsultoresAlocados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultoresAlocadosRepository extends JpaRepository<ConsultoresAlocados, Long> {



}
