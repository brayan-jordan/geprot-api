package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Gestor;
import net.weg.gestor.domain.repository.GestorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class GestorService {

    private GestorRepository gestorRepository;

    @Transactional
    public Gestor cadastrar(Gestor gestor) {
        boolean emailValidation = gestorRepository.findByEmail(gestor.getEmail()).isPresent();
        if (emailValidation) {
            throw new NegocioException("Já existe um usuario com esse email");
        }

        boolean idGestorValidation = gestorRepository.findByidGestor(gestor.getIdGestor()).isPresent();
        if (idGestorValidation) {
            throw new NegocioException("Já existe um gestor com esse ID");
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
            return ResponseEntity.notFound().build();
        }

        gestor.setIdGestor(gestorId);
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
