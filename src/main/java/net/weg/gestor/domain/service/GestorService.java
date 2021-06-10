package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Gestor;
import net.weg.gestor.domain.model.Secao;
import net.weg.gestor.domain.repository.GestorRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class GestorService {

    private GestorRepository gestorRepository;
    private SecaoRepository secaoRepository;

    @Transactional
    public Gestor cadastrar(Gestor gestor) {
        boolean idSecaoValidation = secaoRepository.findById(gestor.getIdsecao()).isPresent();
        if (!idSecaoValidation) {
            throw new NegocioException("ID Da seção é invalido, tente novamente");
        }

        boolean emailValidation = gestorRepository.findByEmail(gestor.getEmail()).isPresent();
        if (emailValidation) {
            throw new NegocioException("Já existe um usuario com esse email");
        }

        boolean idGestorValidation = gestorRepository.findByidgestor(gestor.getIdgestor()).isPresent();
        if (idGestorValidation) {
            throw new NegocioException("Já existe um gestor com esse ID");
        }

        if (gestor.getIdgestor() == 0) {
            throw new NegocioException("ID Inválido");
        }

        return gestorRepository.save(gestor);
    }

    public ResponseEntity<Gestor> excluir(Long gestorId) {
        if(!gestorRepository.existsById(gestorId)) {
            return ResponseEntity.notFound().build();
        }

        gestorRepository.deleteById(gestorId);
        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<Gestor> editar(Long gestorId, Gestor gestor) {
        if(!gestorRepository.existsById(gestorId)) {
            throw new NegocioException("Nao existe um gestor com esse ID para ser editado");
        }

        gestor.setIdgestor(gestorId);
        gestor = gestorRepository.save(gestor);
        return ResponseEntity.ok(gestor);
    }

    public ResponseEntity<Gestor> buscar(Long gestorId) {
        return gestorRepository.findById(gestorId).map(gestor -> ResponseEntity.ok(gestor))
                .orElse(ResponseEntity.notFound().build());
    }

    public List<Gestor> listartodos() {
        return gestorRepository.findAll();
    }


}
