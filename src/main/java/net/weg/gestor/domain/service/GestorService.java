package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.GestorAssembler;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.repository.GestorRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import net.weg.gestor.api.model.GestorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class GestorService {

    private GestorRepository gestorRepository;
    private SecaoRepository secaoRepository;
    private GestorAssembler gestorAssembler;

    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        boolean idSecaoValidation = secaoRepository.findById(usuario.getSecao().getId()).isPresent();
        if (!idSecaoValidation) {
            throw new NegocioException("ID Da seção é invalido, tente novamente");
        }

        usuario.setSecao(secaoRepository.findById2(usuario.getSecao().getId()));

//        boolean emailValidation = gestorRepository.findByEmail(gestor.getUsuario().getEmail()).isPresent();
//        if (emailValidation) {
//            throw new NegocioException("Já existe um usuario com esse email");
//        }

        boolean idGestorValidation = gestorRepository.findByidgestor(usuario.getId()).isPresent();
        if (idGestorValidation) {
            throw new NegocioException("Já existe um gestor com esse ID");
        }

        if (usuario.getId() == 0) {
            throw new NegocioException("ID Inválido");
        }

        return gestorRepository.save(usuario);
    }

    public ResponseEntity<Usuario> excluir(Long gestorId) {
        if(!gestorRepository.existsById(gestorId)) {
            return ResponseEntity.notFound().build();
        }

        gestorRepository.deleteById(gestorId);
        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<Usuario> editar(Long gestorId, Usuario usuario) {
        if(!gestorRepository.existsById(gestorId)) {
            throw new NegocioException("Nao existe um gestor com esse ID para ser editado");
        }

        usuario.setId(gestorId);
        usuario = gestorRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    public ResponseEntity<Usuario> buscar(Long gestorId) {
        return gestorRepository.findById(gestorId).map(gestor -> ResponseEntity.ok(gestor))
                .orElse(ResponseEntity.notFound().build());
    }

    public List<Usuario> listartodos() {
        return gestorRepository.findAll();
    }

    public List<GestorModel> list2() {
        return gestorAssembler.toCollectionModel(gestorRepository.findAll());

    }


}
