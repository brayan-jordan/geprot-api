package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.ConsultorAlocado;
import net.weg.gestor.domain.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultorAlocadoRepository extends JpaRepository<ConsultorAlocado, Long> {

    @Query("SELECT c FROM ConsultorAlocado c where c.consultor = ?1 and c.projeto = ?2")
    Optional<ConsultorAlocado>  verificaSeConsultorEstaAlocado(Consultor consultor, Projeto projeto);

    @Query("SELECT c FROM ConsultorAlocado c where c.consultor = ?1")
    List<ConsultorAlocado> todasDemandasAlocadas(Consultor consultor);

    @Query("SELECT c FROM ConsultorAlocado c where c.projeto = ?1")
    List<ConsultorAlocado> consultoresAlocadosProjeto(Projeto projeto);

}
