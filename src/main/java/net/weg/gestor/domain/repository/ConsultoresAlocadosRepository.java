package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.ConsultoresAlocados;
import net.weg.gestor.domain.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultoresAlocadosRepository extends JpaRepository<ConsultoresAlocados, Long> {

    @Query("SELECT c FROM ConsultoresAlocados c where c.consultor = ?1 and c.projeto = 2?")
    Optional<ConsultoresAlocados>  verificaSeConsultorEstaAlocado(Consultor consultor, Projeto projeto);

}
