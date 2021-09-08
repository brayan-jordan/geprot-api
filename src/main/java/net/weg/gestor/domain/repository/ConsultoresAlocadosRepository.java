package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.ConsultoresAlocados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultoresAlocadosRepository extends JpaRepository<ConsultoresAlocados, Long> {

    @Query("SELECT c FROM ConsultoresAlocados c where c.usuarios_id =?1 and c.projetos_id = ?2")
    ConsultoresAlocados buscar (Long usuarioId, Long projetoId);

    @Query("SELECT c FROM ConsultoresAlocados c where c.usuarios_id =?1")
    ConsultoresAlocados findByIdConsultor(Long usuarioId);

}
