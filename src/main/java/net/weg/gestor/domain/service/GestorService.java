package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Gestor;
import net.weg.gestor.domain.repository.GestorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class GestorService {

    private GestorRepository gestorRepository;

    @Transactional
    public Gestor cadastrar(Gestor gestor) {

//        boolean emailValidation = gestorRepository.findByEmail(gestor.getEmail()).isPresent();
//
//        if (emailValidation) {
//            throw new NegocioException("Já existe um usuario com esse email");
//
//        }

        return gestorRepository.save(gestor);

    }
}
