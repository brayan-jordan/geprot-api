package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.model.ConsultoresAlocados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultoresAlocadosRepository extends JpaRepository<ConsultoresAlocados, Long> {

//    @Query("SELECT c FROM ConsultoresAlocados c where c.usuarios_id =?1 and c.projetos_id = ?2")
//    ConsultoresAlocados buscar (Long usuarioId, Long projetoId);
//
//    @Query("SELECT c FROM ConsultoresAlocados c where c.usuarios_id =?1 and c.projetos_id = ?2")
//    Optional<ConsultoresAlocados> existsVerify(Long usuarioId, Long projetoId);
//
//    @Query("SELECT c FROM ConsultoresAlocados c where c.usuarios_id =?1")
//    List<ConsultoresAlocados> findByIdConsultor2(Long usuarioId);
//
//    @Query("SELECT c FROM ConsultoresAlocados c where c.usuarios_id =?1 and c.projetos_id = ?2")
//    Optional<ConsultoresAlocados> exists (Long usuarioId, Long projetoId);

}
