package net.weg.gestor.domain.repository;

import net.weg.gestor.domain.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    @Query("SELECT f FROM Fornecedor f where f.id = ?1")
    Fornecedor findByIdFornecedor(Long id);
}
